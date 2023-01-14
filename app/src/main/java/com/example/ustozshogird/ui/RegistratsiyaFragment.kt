package com.example.ustozshogird.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.ustozshogird.R

class RegistratsiyaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registratsiya, container, false)

        val back = view.findViewById<ImageView>(R.id.btn_back)

        val signIn = view.findViewById<TextView>(R.id.signIn)

        val btnSignIn = view.findViewById<TextView>(R.id.btn_SignIn)

        btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_registratsiyaFragment_to_mainListFragment)
        }

        back.setOnClickListener {
            findNavController().navigate(R.id.checkFragment)
        }

        signIn.setOnClickListener {
            findNavController().navigate(R.id.action_registratsiyaFragment_to_signUpFragment)
        }
        return view
    }

}