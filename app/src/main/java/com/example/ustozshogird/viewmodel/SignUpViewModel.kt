package com.example.ustozshogird.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ustozshogird.model.data.User
import com.example.ustozshogird.model.repository.RealtimeDBRepository
import com.example.ustozshogird.model.repository.SharedPreferenceRepository
import com.example.ustozshogird.viewmodel.`interface`.SignUpVMInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel(), SignUpVMInterface {
    private val repository = RealtimeDBRepository.getInstance()
    private val storage = SharedPreferenceRepository.getInstance()
    override val messageLiveData = MutableLiveData<String>()
    override val openMainList = MutableLiveData<Unit>()

    override fun signUpClicked(name: String, number: String, password: String, conPassword: String) {
        if (!password.equals(conPassword)) {
            messageLiveData.postValue("Parollar mos kelmadi")
            return
        }
        viewModelScope.launch {
            try {
                repository.checkUser(User("", number, password)).collect {
                    if (it) {
                        messageLiveData.postValue("Ushbu raqam avval ro'yxatdan o'tgan")
                    } else {
                        messageLiveData.postValue("Yozishi kk")
                        try {
                            repository.signUp(User(name, number, password))
                        } catch (e: Exception) {
                            Log.d("TTT", "signUpClicked: ${e.cause}")
                            e.printStackTrace()

                        }
                        openMainList.postValue(Unit)
                        storage.number = number
                    }
                }
            } catch (e: Exception) {
                Log.d("TTT", "signUpClicked: ${e.cause}")

                e.printStackTrace()
            }

            repository.sign(User(name, number, password))
        }
    }
}