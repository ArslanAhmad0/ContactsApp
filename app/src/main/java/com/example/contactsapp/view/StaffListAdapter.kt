package com.example.contactsapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.databinding.StaffRowItemBinding
import com.example.contactsapp.model.StaffList

interface OnClickCallBack{
    fun OnClick(staff: StaffList)
}

class StaffListAdapter(
    private val staffList: List<StaffList>,
    private val ClickCallBack: OnClickCallBack
):RecyclerView.Adapter<StaffListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        StaffListViewHolder(StaffRowItemBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent,false)
        ,ClickCallBack
        )

    override fun onBindViewHolder(holder: StaffListViewHolder, position: Int) {
        if(staffList.isNotEmpty()) holder.onBind(staffList[position])

    }

    override fun getItemCount()=staffList.size
}

class StaffListViewHolder(
    private val binding: StaffRowItemBinding,
    private val ClickCallBack: OnClickCallBack
    ):RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun onBind(staff:StaffList){
            binding.staffIdTextview.text="ID :  ${staff.id}"
            binding.nameTextview.text=staff.firstName +" "+ staff.lastName
            Glide.with(binding.staffImageView)
                .load(staff.avatar)
                .placeholder(R.drawable.noimage)
                .into(binding.staffImageView)

            binding.root.setOnClickListener {
                ClickCallBack.OnClick(staff)
            }
        }

}