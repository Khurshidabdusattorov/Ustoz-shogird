package com.example.ustozshogird.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ustozshogird.R
import com.example.ustozshogird.databinding.FragmentRegistratsiyaBinding
import com.example.ustozshogird.viewmodel.RegistrationViewModel
import com.example.ustozshogird.viewmodel.`interface`.RegistrationVMInterface

class RegistrationFragment : Fragment() {
    private val viewModel: RegistrationVMInterface by viewModels<RegistrationViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRegistratsiyaBinding.inflate(layoutInflater)
        viewModel.openMainList.observe(viewLifecycleOwner, openMainListObserver)
        binding.btnSignIn.setOnClickListener { viewModel.signInClicked(binding.number.text.toString(), binding.password.text.toString()) }

        binding.btnBack.setOnClickListener { findNavController().navigate(R.id.checkFragment) }

        binding.signUp.setOnClickListener {
            viewModel.signUpClicked(binding.number.text.toString(), binding.password.text.toString())
//            findNavController().navigate(R.id.action_registratsiyaFragment_to_signUpFragment)
        }
        return binding.root
    }

    private val openMainListObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_registratsiyaFragment_to_mainListFragment)
    }

    private val showMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}