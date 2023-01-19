package com.example.ustozshogird.viewmodel.`interface`

import androidx.lifecycle.LiveData

interface SignUpVMInterface {

    val messageLiveData: LiveData<String>

    val openMainList: LiveData<Unit>

    fun signUpClicked(name: String, number: String, password: String, conPassword: String)
}