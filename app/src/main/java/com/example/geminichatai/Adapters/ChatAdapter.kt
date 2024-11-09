package com.example.geminichatai.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geminichatai.Models.MessageModel
import com.example.geminichatai.databinding.ItemChatBinding

class ChatAdapter(private val data : List<MessageModel>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (data.isNullOrEmpty()){
            return 0
        }else{
            return data.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(messageModel: MessageModel) {
            binding.message.text = messageModel.message
        }

    }

}