package com.example.ustozshogird.introduction.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.ustozshogird.R
class FirstScreen(private val image:Int) : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.screen_first, container, false)
        view.findViewById<ImageView>(R.id.imageView).setImageResource(image)
        return view
    }
}