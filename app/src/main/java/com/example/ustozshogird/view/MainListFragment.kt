package com.example.ustozshogird.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ustozshogird.R
import com.example.ustozshogird.databinding.FragmentMainListBinding
import com.example.ustozshogird.model.data.Task
import com.example.ustozshogird.view.adapter.MainListAdapter
import com.example.ustozshogird.viewmodel.MainLIstViewModel

class MainListFragment : Fragment() {
    private lateinit var binding: FragmentMainListBinding
    private val viewModel: MainLIstViewModel by viewModels()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainListAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainListBinding.inflate(layoutInflater)
        recycler = binding.rv
        adapter = MainListAdapter(requireContext())
        recycler.adapter = adapter
        viewModel.loadData()
        binding.btnAdd.setOnClickListener { viewModel.openAddFragment() }
        viewModel.tasklist.observe(viewLifecycleOwner, listObserver)
        viewModel.openAddFragment.observe(viewLifecycleOwner, addObserver)
        return binding.root
    }

    private val listObserver = Observer<List<Task>> {
        adapter.submitList(it)
    }

    private val addObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainListFragment_to_addFragment)
    }
}