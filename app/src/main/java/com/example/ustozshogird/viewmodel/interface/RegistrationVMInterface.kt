package com.example.ustozshogird.viewmodel.`interface`

import androidx.lifecycle.LiveData

interface RegistrationVMInterface {

    val forgetPassword: LiveData<String>
    val openMainList: LiveData<Unit>

    val signUpLiveData: LiveData<Unit>

    val signInButtonLiveData: LiveData<Unit>

    val messageLiveData: LiveData<String>

    fun signInClicked(number: String, password: String)
    fun signUpClicked(number: String, password: String)
}