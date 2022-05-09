package com.example.contactsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentSecondBinding
import com.example.contactsapp.model.LoadingState
import com.example.contactsapp.viewmodel.StaffViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModel<StaffViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun configureObservers(){
        viewModel.roomList.observe(viewLifecycleOwner
        ) {
            binding.rvRoomsList.apply {
                adapter = RoomsListAdapter(it)
                layoutManager = GridLayoutManager(context, 1)

            }
            viewModel.loadingState.observe(viewLifecycleOwner){
                    state-> when(state){
                LoadingState.LOADED-> {
                    binding.apply {
                        rvRoomsList.visibility = View.VISIBLE
                        progressBarRooms.visibility=View.GONE
                    }
                }
                LoadingState.LOADING->{
                    binding.apply {
                        rvRoomsList.visibility = View.GONE
                        progressBarRooms.visibility=View.VISIBLE
                    }
                }
            }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureObservers()
        viewModel.fetchRooms()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}