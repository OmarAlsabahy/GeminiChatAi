package com.example.geminichatai.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.geminichatai.Models.MessageModel
import com.example.geminichatai.R
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
            if (!messageModel.fromUser){
               binding.message.setBackgroundResource(R.color.white)
                binding.message.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
                binding.linearLayout.gravity = android.view.Gravity.END
            }else{
                binding.message.setBackgroundResource(R.color.mainColor)
                binding.message.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                binding.linearLayout.gravity = android.view.Gravity.START
            }
            binding.message.text = messageModel.message
        }

    }

}