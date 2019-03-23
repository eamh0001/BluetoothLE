package com.eamh.bluetoothle.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eamh.bluetoothle.R

@BindingAdapter("imageResource")
fun ImageView.setImageResource(resourceId: Int) {
    setImageResource(resourceId)
}

@BindingAdapter("textId")
fun TextView.setTextId(textId: Int){
    if (textId != 0){
        this.setText(textId)
    }else
        this.setText(R.string.empty)
}