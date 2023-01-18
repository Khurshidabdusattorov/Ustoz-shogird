package com.example.ustozshogird.model.repository

import android.content.Context
import com.example.ustozshogird.app.App

class SharedPreferenceRepository {
    companion object{
        var ins : SharedPreferenceRepository? = null
        fun getInstance() : SharedPreferenceRepository{
            if (ins == null) ins= SharedPreferenceRepository()
            return ins!!
        }
    }

    private val sharedPreferences = App.instance.getSharedPreferences("Data", Context.MODE_PRIVATE)

    var number: String

        get() {
            return sharedPreferences.getString("Number", "")!!
        }

        set(value) {
            sharedPreferences.edit().putString("Number", value).apply()
        }

    var isFirst: Boolean
        get() {
            return sharedPreferences.getBoolean("isFirst", true)
        }

        set(value) {
            sharedPreferences.edit().putBoolean("isFirst", false).apply()
        }
}