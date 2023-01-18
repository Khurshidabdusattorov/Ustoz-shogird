package com.example.ustozshogird.view.introduction

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ustozshogird.R
import com.example.ustozshogird.viewmodel.SplashViewModel
import com.google.firebase.database.FirebaseDatabase

class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.launch()
        viewModel.openIntro.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_splashFragment_to_introFragment)
        }
        viewModel.openRegistration.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_splashFragment_to_checkFragment)
        }
        viewModel.openMainList.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_splashFragment_to_mainListFragment)
        }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}