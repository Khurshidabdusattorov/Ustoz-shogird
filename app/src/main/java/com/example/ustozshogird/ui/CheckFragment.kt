package com.example.ustozshogird.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.ustozshogird.R

class CheckFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_check, container, false)


        val ustoz = view.findViewById<TextView>(R.id.btn_Ustoz)
        val shogird = view.findViewById<TextView>(R.id.btn_Shogird)

        ustoz.setOnClickListener {
            findNavController().navigate(R.id.action_checkFragment_to_registratsiyaFragment)
        }

        shogird.setOnClickListener {
            findNavController().navigate(R.id.action_checkFragment_to_registratsiyaFragment)
        }

        return view
    }
}