package com.example.contactsapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.RoomsRowItemBinding
import com.example.contactsapp.model.RoomList
class RoomsListAdapter (private val roomList:List<RoomList>): RecyclerView.Adapter<RoomsListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            RoomsListViewHolder(
                RoomsRowItemBinding.inflate(
                LayoutInflater.from(parent.context)
                ,parent,false)
                ,parent.context
            )

        override fun onBindViewHolder(holder: RoomsListViewHolder, position: Int) {
            holder.onBind(roomList[position])
        }

        override fun getItemCount()=roomList.size
    }

    class RoomsListViewHolder(
        private val binding: RoomsRowItemBinding,
        private val context: Context
    ): RecyclerView.ViewHolder(binding.root){
        private var roomStatus =""
        @SuppressLint("SetTextI18n")
        fun onBind(rooms: RoomList){
            binding.apply {
                roomIdTView.text="Room ID : ${rooms.id}"

               if(rooms.isOccupied){
                   roomStatus  = "occupied"
                   roomStatusTView.text= roomStatus
               }
                else{
                   roomStatus="not occupied"
                    roomStatusTView.text= roomStatus
                }


            }
            binding.root.setOnClickListener {
                Toast.makeText(context, "Room No: ${rooms.id} is $roomStatus ", Toast.LENGTH_SHORT).show()
            }
        }
    }