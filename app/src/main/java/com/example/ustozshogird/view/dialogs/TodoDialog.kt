package com.example.ustozshogird.view.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.ustozshogird.R
import com.example.ustozshogird.databinding.DialogTodoBinding

class TodoDialog(ctx: Context) : Dialog(ctx) {

    private lateinit var binding: DialogTodoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogTodoBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)

        binding.btnDialogNo.setOnClickListener {
            binding.btnDialogNo.findViewById<AppCompatButton>(R.id.btn_dialog_no)
        }
        binding.btnDialogYes.setOnClickListener {
            binding.btnDialogYes.setOnClickListener {
                binding.btnDialogYes.findViewById<AppCompatButton>(R.id.btn_dialog_yes)
            }
        }
    }
}