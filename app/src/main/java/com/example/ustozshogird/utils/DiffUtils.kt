package com.example.ustozshogird.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffUtils<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem==newItem
}