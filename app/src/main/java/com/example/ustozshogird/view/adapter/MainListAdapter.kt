package com.example.ustozshogird.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ustozshogird.R
import com.example.ustozshogird.model.data.Task
import com.example.ustozshogird.utils.DiffUtils
import java.text.SimpleDateFormat
import java.util.*

class MainListAdapter(val context: Context) : ListAdapter<Task, MainListAdapter.VH>(DiffUtils<Task>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.onBind(currentList[position])

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val locale = Locale("uz", "UZ")
        val image = view.findViewById<ImageView>(R.id.imageView)
        val time = view.findViewById<TextView>(R.id.time)
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)

        @SuppressLint("SetTextI18n")
        fun onBind(item: Task) {
            Glide.with(context).load(item.imageUrl).into(image)
            time.text = SimpleDateFormat("HH:mm", locale).format(item.timeStamp) + ", " + SimpleDateFormat("dd.MM.yyyy", locale).format(item.timeStamp)
            title.text = item.title
            description.text = item.description
        }
    }
}


