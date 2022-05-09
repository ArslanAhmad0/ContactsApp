package com.example.contactsapp.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentFirstBinding
import com.example.contactsapp.model.LoadingState
import com.example.contactsapp.model.StaffList
import com.example.contactsapp.viewmodel.StaffViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() ,OnClickCallBack {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<StaffViewModel>()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)


                binding.swipeRefreshStaffLayout.setOnRefreshListener {
                    if (checkForInternet(context)) {
                        viewModel.fetchStaff()

                    }
                    else {
                        binding.rvStaffList.visibility =View.GONE
                        binding.errorTv.visibility = View.VISIBLE

                    }
                    binding.swipeRefreshStaffLayout.isRefreshing = false
                }


        return binding.root
    }
    private fun configureObservers(){
        viewModel.staffList.observe(viewLifecycleOwner
        ) {
            binding.rvStaffList.apply {
                if (!it.isNullOrEmpty()) adapter = StaffListAdapter(it,this@FirstFragment)
                else
                {
                    viewModel.fetchStaff()
                }

            }
            viewModel.loadingState.observe(viewLifecycleOwner){
                state-> when(state){
                    LoadingState.LOADED-> {
                        binding.apply {
                            rvStaffList.visibility = View.VISIBLE
                            progressBarStaff.visibility=View.GONE
                            errorTv.visibility = View.GONE
                        }
                    }
                    LoadingState.LOADING->{
                        binding.apply {
                            rvStaffList.visibility = View.GONE
                            progressBarStaff.visibility=View.VISIBLE
                            errorTv.visibility = View.GONE
                        }
                    }
                    LoadingState.ERROR->{
                        binding.apply {
                            rvStaffList.visibility = View.GONE
                            errorTv.visibility = View.VISIBLE
                            progressBarStaff.visibility=View.GONE

                        }
                    }
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureObservers()
        if (checkForInternet(context))
            viewModel.fetchStaff()
        else{
            binding.errorTv.visibility = View.VISIBLE
            binding.rvStaffList.visibility = View.GONE


        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun OnClick(staff: StaffList) {
        val bundle = Bundle()
        bundle.putSerializable("StaffArg",staff)
        findNavController().navigate(R.id.action_FirstFragment_to_staffDataFragment,bundle)
    }

    private fun checkForInternet(context: Context?): Boolean {

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}
