package com.example.uicustomviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.uicustomviews.databinding.TitleBinding

class TitleLayout(context: Context,attr:AttributeSet) : ConstraintLayout(context,attr){

    init {
        LayoutInflater.from(context).inflate(R.layout.title,this)
    }

}