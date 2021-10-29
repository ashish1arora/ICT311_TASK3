package com.usc.workoutapp.ui

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.usc.workoutapp.R
import com.usc.workoutapp.ui.fragments.FragmentActivityDetails
import com.usc.workoutapp.ui.fragments.FragmentActivityList
import com.usc.workoutapp.utils.Utils

/*
* This is a primary activity class which manages Fragments for this APP
*
* */
class MainActivity : AppCompatActivity(), FragmentActivityList.AddIconListener {

    private var activityPos = Utils.DEFAULT_POS

    // Android's CallBack when this Activity is being created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check whether this is a Device Configuration change
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                activityPos = getInt(Utils.ACTIVITY_POS)
            }
        } else {
            // Loads List Fragment by default
            goToFragment(FragmentActivityList.getInstance(), Nav.LIST, false)
        }
    }

    // Android's Callback when Device Configuration change is being observed
    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run { putInt(Utils.ACTIVITY_POS, activityPos) }

        super.onSaveInstanceState(outState)
    }

    // Switch to the Fragment which is passed as argument
    private fun goToFragment(
        @NonNull fragment: Fragment, @MainActivity.Nav navID: String,
        addToStack: Boolean
    ) {
        try {
            if (navID == Nav.LIST) {
                activityPos = Utils.DEFAULT_POS;
            }

            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            if (addToStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.replace(R.id.fragment_holder, fragment)
            fragmentTransaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Switch to Activity's Details Fragment
    override fun switchToActivityDetails(actPos: Int) {
        activityPos = actPos

        // Position of Activity Clicked by user in Activities List
        val argsBundle = Bundle()
        argsBundle.putInt(Utils.ACTIVITY_POS, actPos)

        val detailFragment: FragmentActivityDetails = FragmentActivityDetails.getInstance()
        detailFragment.arguments = argsBundle

        goToFragment(detailFragment, Nav.DETAILS, true)
    }

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    internal annotation class Nav {
        companion object {
            var LIST = "LIST"
            var DETAILS = "DETAIL"
        }
    }
}