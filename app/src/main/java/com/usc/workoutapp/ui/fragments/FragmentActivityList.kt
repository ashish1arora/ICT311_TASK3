package com.usc.workoutapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.usc.workoutapp.R
import com.usc.workoutapp.adapter.ActivityAdapter
import com.usc.workoutapp.database.DBHandler
import com.usc.workoutapp.utils.ActivityDetails
import com.usc.workoutapp.utils.Utils

/*
* Fragment class to display Activities List
* It fetches Activities from Database and Updates List
*
* */
class FragmentActivityList : Fragment(), ActivityAdapter.ItemCellClickListener {

    private lateinit var recycleViewActivities: RecyclerView
    private lateinit var activitiesAdapter: ActivityAdapter

    private lateinit var addIcon: ImageView
    private lateinit var addClickListener: AddIconListener

    private var activityList = ArrayList<ActivityDetails>()
    private var dbHandler: DBHandler? = null

    // Android's Callback when Fragment is attached to an Activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddIconListener) {
            addClickListener = context
        }
    }

    // Android's Callback when Fragment's View is created
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_activity_list, container, false)

        addIcon = view.findViewById(R.id.toolbar_add_button)
        recycleViewActivities = view.findViewById(R.id.rv_activities)

        initializeViews()

        return view;
    }

    // Initialize Views for this Fragment
    private fun initializeViews() {
        dbHandler = activity?.let { DBHandler.getInstance(it) }

        addIcon.setOnClickListener { addClickListener.switchToActivityDetails(Utils.NEW_POS) }

        activitiesAdapter = ActivityAdapter(activityList, this)

        recycleViewActivities.layoutManager = LinearLayoutManager(activity)
        recycleViewActivities.itemAnimator = DefaultItemAnimator()
        recycleViewActivities.adapter = activitiesAdapter
    }

    // Android's Callback when Fragment is going to be visible
    override fun onResume() {
        super.onResume()

        // Fetch latest data from DB
        fetchActivitiesFromDB()
    }

    // Fetch Activities from DB and update List
    private fun fetchActivitiesFromDB() {
        Thread {
            val workoutList: List<ActivityDetails> =
                dbHandler?.daoAccess()?.fetchActivities() as List<ActivityDetails>

            // Show a message to User when an Activity is inserted
            if (activityList.size > 0 && workoutList.size > activityList.size) {
                activity?.runOnUiThread(Runnable {
                    Toast.makeText(context, Utils.MSG_INSERT, Toast.LENGTH_SHORT).show()
                })
            }

            // Update Master list
            activityList = workoutList as ArrayList<ActivityDetails>

            // Refresh Recycler View after getting data from Database
            activity?.runOnUiThread(Runnable {
                activitiesAdapter.refreshActivityList(activityList)
                activitiesAdapter.notifyDataSetChanged()
            })
        }.start()
    }

    // Create Instance for this Fragment
    companion object {
        @JvmStatic
        fun getInstance() = FragmentActivityList().apply {
        }
    }

    // Add_Icon's Click Listener
    interface AddIconListener {
        fun switchToActivityDetails(actPos: Int)
    }

    // Activities List Click Listener
    override fun onActivityClicked(pos: Int) {
        addClickListener.switchToActivityDetails(pos)
    }
}