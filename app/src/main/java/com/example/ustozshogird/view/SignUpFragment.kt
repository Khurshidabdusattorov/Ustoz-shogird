package com.example.ustozshogird.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ustozshogird.R
import com.example.ustozshogird.databinding.FragmentSignUpBinding
import com.example.ustozshogird.model.data.User
import com.example.ustozshogird.viewmodel.SignUpViewModel
import com.example.ustozshogird.viewmodel.`interface`.SignUpVMInterface
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpVMInterface by viewModels<SignUpViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentSignUpBinding.inflate(layoutInflater)
        binding.btnBackSignIn.setOnClickListener {
            findNavController().navigate(R.id.registratsiyaFragment)
        }
        binding.btnSignUP.setOnClickListener {
            viewModel.signUpClicked(

                binding.signInName.text.toString(),
                binding.signInPhoneNUmber.text.toString(),
                binding.signInPassword.text.toString(),
                binding.signInPasswordTO.text.toString()
            )
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner, messageObserver)
        return binding.root
    }

    private val messageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}