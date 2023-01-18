package com.example.ustozshogird.model.data

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val timeStamp: String,
    val imageUrl: String
) : java.io.Serializable
