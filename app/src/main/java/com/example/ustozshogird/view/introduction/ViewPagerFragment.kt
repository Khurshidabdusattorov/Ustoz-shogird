package com.example.ustozshogird.view.introduction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ustozshogird.R
import com.example.ustozshogird.view.adapter.ViewPagerAdapter
import com.example.ustozshogird.view.introduction.screen.FirstScreen

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_viewpager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(R.drawable.intro1),
            FirstScreen(R.drawable.intro2),
            FirstScreen(R.drawable.intro3)
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val title = view.findViewById<TextView>(R.id.title)
        val next = view.findViewById<TextView>(R.id.next)
        val pager = view.findViewById<ViewPager2>(R.id.viewPager)
        pager.adapter = adapter
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                   0-> {
                       title.text = "Masofaviy ta'lim"
                       next.setOnClickListener { pager.setCurrentItem(1,true) }
                   }
                    1-> {
                        title.text = "Online team"
                        next.text = "Next"
                        next.setOnClickListener { pager.setCurrentItem(2,true) }
                    }
                    2-> {
                        title.text = "Grow Up"
                        next.text = "Finish"
                        next.setOnClickListener { findNavController().navigate(R.id.action_introFragment_to_checkFragment) }
                    }
                }
            }
        })
        return view
    }
}