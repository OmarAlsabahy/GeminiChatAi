package com.example.geminichatai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.geminichatai.ViewModels.LoginViewModel
import com.example.geminichatai.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding:FragmentLoginBinding
    val viewModel : LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.bind(view)
        binding.txtCreateNewAccount.setOnClickListener {
            navigateToRegister()
        }
        binding.btnLogin.setOnClickListener {
            validateUser()
        }
    }

    private fun displayProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun validateUser() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        if (email.isEmpty()){
            binding.editTextEmail.error = "Email is required"
        }
        else if (password.toString().isEmpty()){
            binding.editTextPassword.error = "Password is required"
        }else{
            displayProgressBar()
            viewModel.login(email , password)
            viewModel.status.observe(viewLifecycleOwner){
                if(it){
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToChatFragment())
                }else{
                    Toast.makeText(requireContext() , "Login Failed" , Toast.LENGTH_LONG).show()
                }
                hideProgressBar()
            }
        }
    }

    private fun hideProgressBar(){
        binding.progressbar.visibility = View.GONE
    }

    private fun navigateToRegister() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }
}