package com.example.ustozshogird.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ustozshogird.model.repository.RealtimeDBRepository
import com.example.ustozshogird.model.repository.SharedPreferenceRepository
import com.example.ustozshogird.viewmodel.`interface`.SplashVMInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel(),SplashVMInterface{
    private val repositoryFirebase = RealtimeDBRepository()
    private val storage = SharedPreferenceRepository()
    private val _openIntro=MutableLiveData<Unit>()
    val openIntro :LiveData<Unit> = _openIntro
    private val _openRegistration=MutableLiveData<Unit>()
    val openRegistration :LiveData<Unit> = _openRegistration
    private val _openMainList=MutableLiveData<String>()
    val openMainList :LiveData<String> = _openMainList
    override fun launch() {
        viewModelScope.launch {
            delay(3000)
            isFirst()
        }
    }

    override fun isFirst() {
        if (storage.isFirst){
            _openIntro.postValue(Unit)
            storage.isFirst = false
        }else{
            isSignIn()
        }
    }

    override fun isSignIn() {
        if (storage.number!=""){
            _openMainList.postValue(storage.number)
        }else{
            _openRegistration.postValue(Unit)
        }
    }
}