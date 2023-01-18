package com.example.ustozshogird.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ustozshogird.R

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

       val btnback = view.findViewById<ImageView>(R.id.btn_backSignIn)

        btnback.setOnClickListener {
            findNavController().navigate(R.id.registratsiyaFragment)
        }
        return view
    }

}