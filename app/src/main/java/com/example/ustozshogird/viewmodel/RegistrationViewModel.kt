package com.example.ustozshogird.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ustozshogird.model.data.User
import com.example.ustozshogird.model.repository.RealtimeDBRepository
import com.example.ustozshogird.viewmodel.`interface`.RegistrationVMInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel(), RegistrationVMInterface {

    private val repository = RealtimeDBRepository.getInstance()

    override val forgetPassword = MutableLiveData<String>()

    override val openMainList = MutableLiveData<Unit>()

    override val signUpLiveData = MutableLiveData<Unit>()

    override val signInButtonLiveData = MutableLiveData<Unit>()

    override val messageLiveData = MutableLiveData<String>()

    override fun signInClicked(number: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.checkUser(User("", number, password)).collect {
//                if (it) {
//                    openMainList.postValue(Unit)
//                } else {
//                    messageLiveData.postValue("Ushbu foydalanuvchi topilmadi")
//                }
//            }
            if (repository.check(User("", number, password))) {

                openMainList.postValue(Unit)

            } else {

                    messageLiveData.postValue("Ushbu foydalanuvchi topilmadi")

                }
            }
    }

    override fun signUpClicked() {
        signUpLiveData.postValue(Unit)
//        viewModelScope.launch(Dispatchers.Main) {
//            Log.d("TTT", "signUpClicked: ")
//            repository.signUp(User()).collect {
//                Log.d("TTT", "signUpClicked: $it")
//                if(it){
//                    println("true")
//                }
//            }
//        }
    }
}