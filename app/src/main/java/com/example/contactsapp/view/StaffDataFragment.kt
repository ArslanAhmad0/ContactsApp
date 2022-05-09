package com.example.contactsapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentStaffdataBinding
import com.example.contactsapp.model.StaffList

class StaffDataFragment : Fragment() {
        private lateinit var binding: FragmentStaffdataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStaffdataBinding.inflate(layoutInflater,container,false)
        return binding.root



    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val staff = requireArguments().getSerializable("StaffArg") as StaffList
        binding.apply {

            staffColorTV.text =  staff.favouriteColor
            staffEmailTV.text = staff.email
            staffIDTV.text = staff.id.toString()
            staffJobTV.text = staff.jobtitle
            staffNameTV.text = "${staff.firstName} ${staff.lastName}"
            dateCreatedTV.text = staff.createdAt

            Glide.with(staffImageView)
                .load(staff.avatar)
                .placeholder(R.drawable.noimage)
                .into(staffImageView)

        }
    }
}