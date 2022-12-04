package com.example.mobile_systems_frontend_new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_systems_frontend_new.model.Signal

class RecyclerAdapter(private var items: ArrayList<Signal>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val title = "Signalas: " + items[position].matavimas.toString()
        val xVal = "X: " + items[position].x.toString()
        val yVal = "Y: " + items[position].y.toString()
        val distance = "Atstumas: " + items[position].atstumas.toString()

        holder.itemTitle.text = title
        holder.itemXVal.text = xVal
        holder.itemYVal.text = yVal
        holder.itemDist.text = distance
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemXVal: TextView
        var itemYVal: TextView
        var itemDist: TextView

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            itemXVal = itemView.findViewById(R.id.item_xVal)
            itemYVal = itemView.findViewById(R.id.item_yVal)
            itemDist = itemView.findViewById(R.id.item_dist)
        }
    }
}