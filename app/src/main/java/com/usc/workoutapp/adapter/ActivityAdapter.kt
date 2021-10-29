package com.usc.workoutapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usc.workoutapp.R
import com.usc.workoutapp.utils.ActivityDetails

/*
* Adapter to Update Activities in List
*
* */
class ActivityAdapter(
    private var mList: List<ActivityDetails>,
    private val itemCellClickListener: ActivityAdapter.ItemCellClickListener
) :
    RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        return ActivityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
        )
    }

    // Update List Items
    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = mList[position]

        holder.title.text = activity.title
        holder.date.text = activity.date
        holder.place.text = activity.place

        holder.itemView.setOnClickListener {
            val selActivity = mList[position]

            itemCellClickListener.onActivityClicked(selActivity._id)
        }
    }

    // List Size
    override fun getItemCount(): Int {
        return mList.size
    }

    // New List
    fun refreshActivityList(list: List<ActivityDetails>) {
        mList = list
    }

    // Class which holds UI Items
    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_activity_title)
        val date: TextView = itemView.findViewById(R.id.tv_activity_date)
        val place: TextView = itemView.findViewById(R.id.tv_activity_place)
    }

    // Click Listener for List
    interface ItemCellClickListener {
        fun onActivityClicked(pos: Int)
    }
}