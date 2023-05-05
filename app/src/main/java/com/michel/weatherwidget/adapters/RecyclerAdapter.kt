package com.michel.weatherwidget.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (
    private val cities: List<String>,
    ): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(null, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //holder.setData(recordCards[position], recordCards, recorder, activity)
        }

        //Returns item count in RecyclerView
        override fun getItemCount() = cities.size

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        }
}