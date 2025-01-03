package com.example.geminichatai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geminichatai.Adapters.ChatAdapter
import com.example.geminichatai.Models.MessageModel
import com.example.geminichatai.ViewModels.ChatViewModel
import com.example.geminichatai.databinding.FragmentChatFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatFragmentBinding
    val dispatcher = OnBackPressedDispatcher()
    val viewModel : ChatViewModel by viewModels()
    lateinit var adapter: ChatAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentChatFragmentBinding.bind(view)

        val onBackPressed = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        }
        dispatcher.addCallback(onBackPressed)


        viewModel.getAllMessages()
        viewModel.messages.observe(viewLifecycleOwner){
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true

            }
            adapter = ChatAdapter(it)
            adapter.notifyDataSetChanged()
            binding.recyclerView.adapter = adapter
        }

        binding.btnSend.setOnClickListener {
            validateData()
            clearBar()
        }
    }

    private fun clearBar() {
        binding.chatBar.text.clear()
    }

    private fun validateData() {
        val message = binding.chatBar.text.toString()
        if (message.isEmpty()){
            binding.chatBar.error = "Please enter a message"
        }else{

            sendMessage(message)
        }
    }

    private fun sendMessage(message: String) {
        viewModel.sendMessage(message)
    }
}