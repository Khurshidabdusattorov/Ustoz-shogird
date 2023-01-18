package com.example.ustozshogird.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ustozshogird.model.data.Task
import com.example.ustozshogird.model.repository.RealtimeDBRepository
import com.example.ustozshogird.model.repository.SharedPreferenceRepository
import com.example.ustozshogird.viewmodel.`interface`.MainListVMInterface
import kotlinx.coroutines.launch

class MainLIstViewModel : ViewModel(), MainListVMInterface {
    private val storage = SharedPreferenceRepository.getInstance()
    private val repository = RealtimeDBRepository.getInstance()
    override val openAddFragment = MutableLiveData<Unit>()
    override val tasklist = MutableLiveData<List<Task>>()

    override fun loadData() {
        viewModelScope.launch {
            repository.getTasks(storage.number).collect {
                tasklist.postValue(it)
            }
        }
    }

    override fun openAddFragment() {
        openAddFragment.postValue(Unit)
    }
}