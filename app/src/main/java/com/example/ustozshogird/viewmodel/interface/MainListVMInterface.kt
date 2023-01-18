package com.example.ustozshogird.viewmodel.`interface`

import androidx.lifecycle.LiveData
import com.example.ustozshogird.model.data.Task

interface MainListVMInterface {
    val openAddFragment: LiveData<Unit>
    val tasklist: LiveData<List<Task>>

    fun loadData()
    fun openAddFragment()
}