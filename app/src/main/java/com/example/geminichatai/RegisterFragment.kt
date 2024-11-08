package com.example.geminichatai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.geminichatai.ViewModels.RegisterViewModel
import com.example.geminichatai.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    val viewModel : RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRegisterBinding.bind(view)
        binding.btnRegister.setOnClickListener {
            validateUser()
        }
    }

    private fun validateUser() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if (email.isEmpty()){
            binding.editTextEmail.error = "Email is required"
        }
        else if (password.isEmpty()){
            binding.editTextPassword.error = "Password is required"
        }else if (binding.editTextConfirmPassword.text.toString().isEmpty()){
            binding.editTextConfirmPassword.error = "Confirm password is required"
        }else if(!password.equals(confirmPassword)){
            Toast.makeText(requireContext() , "Password not match" , Toast.LENGTH_LONG).show()
        }else{
            displayProgressBar()
            viewModel.register(email , password)
            viewModel.status.observe(viewLifecycleOwner){
                if (it){
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToChatFragment())
                }else{
                    Toast.makeText(requireContext() , "Register Failed" , Toast.LENGTH_LONG).show()
                }
                hideProgressBar()
            }
        }

    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }

    private fun displayProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }

}