package com.usc.workoutapp.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.usc.workoutapp.R
import com.usc.workoutapp.database.DBHandler
import com.usc.workoutapp.utils.ActivityDetails
import com.usc.workoutapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

/*
* Fragment class to hold an Activity's Detail
* It takes User Input and updates/inserts/deletes Data in/from Database
*
* */
class FragmentActivityDetails : Fragment() {

    private lateinit var addIcon: ImageView

    private lateinit var activityTitle: EditText
    private lateinit var activityPlace: EditText
    private lateinit var activityStartTime: EditText
    private lateinit var activityEndTime: EditText

    private lateinit var saveBut: Button
    private lateinit var updateBut: Button
    private lateinit var deleteBut: Button
    private lateinit var activityDateBut: Button

    private lateinit var radioGroup: RadioGroup

    private lateinit var llViewUpdate: LinearLayout
    private lateinit var llViewNew: LinearLayout

    private var activityType = Utils.RG_TAG_INDIVIDUAL
    private var selActivityPos = Utils.NEW_POS

    private val calendarObj = Calendar.getInstance()

    private var dbHandler: DBHandler? = null
    private var activityDetails: ActivityDetails? = null

    // Android's Callback when this Fragment's View is created
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_activity_details, container, false)

        // Position of Activity clicked in Activities List
        if (arguments != null) {
            selActivityPos = requireArguments().getInt(Utils.ACTIVITY_POS)
        }

        dbHandler = activity?.let { DBHandler.getInstance(it) }

        // Initialize Views with proper Data
        initializeViews(view, selActivityPos)

        // If User Presses Back Button and required Data is available insert New Activity in DB
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val errorMsg = validateData()

                    if (selActivityPos == Utils.NEW_POS && errorMsg == null) {
                        val workoutDetails = ActivityDetails(
                            0, activityTitle.text.toString(),
                            activityDateBut.text.toString(),
                            activityPlace.text.toString(),
                            activityStartTime.text.toString(),
                            activityEndTime.text.toString(),
                            activityType
                        )

                        Thread {
                            dbHandler?.daoAccess()?.insertWorkout(workoutDetails)
                        }.start()
                    }
                    closeFragment()
                }
            })

        return view
    }

    // Initialize Views and Update with Proper Data
    private fun initializeViews(view: View, selActivityPos: Int) {
        addIcon = view.findViewById(R.id.toolbar_add_button)

        activityTitle = view.findViewById(R.id.et_activity_title)
        activityPlace = view.findViewById(R.id.et_activity_place)
        activityStartTime = view.findViewById(R.id.et_activity_starttime)
        activityEndTime = view.findViewById(R.id.et_activity_endtime)

        saveBut = view.findViewById(R.id.btn_activity_save)
        updateBut = view.findViewById(R.id.btn_activity_update)
        deleteBut = view.findViewById(R.id.btn_activity_delete)
        activityDateBut = view.findViewById(R.id.btn_activity_date)

        radioGroup = view.findViewById(R.id.rg_activity_type)

        llViewNew = view.findViewById(R.id.ll_activity_save)
        llViewUpdate = view.findViewById(R.id.ll_activity_update)

        addIcon.visibility = View.GONE

        activityStartTime.setOnClickListener {
            initStartTime()
        }

        activityEndTime.setOnClickListener {
            initEndTime()
        }

        activityDateBut.setOnClickListener {
            var year: Int
            var month: Int
            var day: Int
            var list: List<String>

            // Consider User selected Date otherwise use Current Date
            if (activityDateBut.text.toString().isNotEmpty()) {
                list = activityDateBut.text.toString().split("/")

                day = Integer.parseInt(list[0])
                month = Integer.parseInt(list[1])
                year = Integer.parseInt(list[2])
            } else {
                year = calendarObj.get(Calendar.YEAR)
                month = calendarObj.get(Calendar.MONTH)
                day = calendarObj.get(Calendar.DAY_OF_MONTH)
            }

            val datePicker =
                activity?.let { it ->
                    DatePickerDialog(it, { _, year, monthOfYear, dayOfMonth ->
                        // Update Selected Date
                        val selMonth = monthOfYear + 1
                        activityDateBut.setText("""$dayOfMonth/$selMonth/$year""")
                    }, year, month - 1, day)
                }
            datePicker?.show()
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_activity_individual -> {
                    activityType = Utils.RG_TAG_INDIVIDUAL
                }
                R.id.rb_activity_group -> {
                    activityType = Utils.RG_TAG_GROUP
                }
            }
        }

        // If View is for a New Activity
        if (selActivityPos == Utils.NEW_POS) {
            saveBut.visibility = View.VISIBLE
            deleteBut.visibility = View.GONE
            updateBut.visibility = View.GONE

            radioGroup.check(R.id.rb_activity_individual)

            val currentTime: String =
                SimpleDateFormat(
                    Utils.DATE_PATTERN,
                    Locale.getDefault()
                ).format(System.currentTimeMillis())

            activityDateBut.setText(currentTime)

            saveBut.setOnClickListener {

                val errorMsg = validateData()
                if (errorMsg != null) {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                } else {
                    val workoutDetails = ActivityDetails(
                        0, activityTitle.text.toString(),
                        activityDateBut.text.toString(),
                        activityPlace.text.toString(),
                        activityStartTime.text.toString(),
                        activityEndTime.text.toString(),
                        activityType
                    )

                    Thread {
                        dbHandler?.daoAccess()?.insertWorkout(workoutDetails)

                        closeFragment()
                    }.start()
                }
            }
        } else {
            // When an existing Activity is Clicked
            fetchActivityFromDB()

            saveBut.visibility = View.GONE
            deleteBut.visibility = View.VISIBLE
            updateBut.visibility = View.VISIBLE

            deleteBut.setOnClickListener {
                showConfirmDialog(
                    Utils.MSG_DELETE,
                    Utils.ALERT_DELETE
                )
            }

            updateBut.setOnClickListener {
                val errorMsg = validateData()
                if (errorMsg != null) {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                } else {
                    showConfirmDialog(
                        Utils.MSG_UPDATE,
                        Utils.ALERT_UPDATE
                    )
                }
            }
        }
    }

    // Fetch Activity Details from DB
    private fun fetchActivityFromDB() {
        Thread {
            Looper.prepare()

            activityDetails = dbHandler?.daoAccess()?.fetchActivity(selActivityPos)

            if (activityDetails != null) {
                activityTitle.setText(activityDetails?.title)
                activityPlace.setText(activityDetails?.place)
                activityDateBut.setText(activityDetails?.date)
                activityStartTime.setText(activityDetails?.starttime)
                activityEndTime.setText(activityDetails?.endtime)

                if (activityDetails?.type == Utils.RG_TAG_INDIVIDUAL)
                    radioGroup.check(R.id.rb_activity_individual)
                else
                    radioGroup.check(R.id.rb_activity_group)
            }
        }.start()
    }

    // Update an Activity after User's Confirmation
    private fun updateActivity() {
        activityDetails?.title = activityTitle.text.toString()
        activityDetails?.date = activityDateBut.text.toString()
        activityDetails?.place = activityPlace.text.toString()
        activityDetails?.starttime = activityStartTime.text.toString()
        activityDetails?.endtime = activityEndTime.text.toString()
        activityDetails?.type = activityType

        Thread {
            dbHandler?.daoAccess()?.updateActivity(activityDetails)

            closeFragment()
        }.start()
    }

    // Delete an Activity after User's Confirmation
    private fun deleteActivity() {
        Thread {
            dbHandler?.daoAccess()?.deleteActivity(activityDetails)

            closeFragment()
        }.start()
    }

    // Initialize and Fetch StartTime for this Activity
    private fun initStartTime() {
        var hours: Int
        var minutes: Int

        // Consider User selected Time otherwise use Current Time
        if (activityStartTime.text.toString().isNotEmpty()) {
            val splitList = activityStartTime.text.toString().split(":")

            hours = Integer.parseInt(splitList[0])
            minutes = Integer.parseInt(splitList[1])
        } else {
            hours = calendarObj.get(Calendar.HOUR)
            minutes = calendarObj.get(Calendar.MINUTE)
        }

        TimePickerDialog(activity, { _, h, m ->
            activityStartTime.setText(String.format("%02d:%02d", h, m))
        }, hours, minutes, false)
            .show()
    }

    // Initialize and Fetch EndTime for this Activity
    private fun initEndTime() {
        var hours: Int
        var minutes: Int

        // Consider User selected Time otherwise use Current Time
        if (activityEndTime.text.toString().isNotEmpty()) {
            val splitList = activityEndTime.text.toString().split(":")

            hours = Integer.parseInt(splitList[0])
            minutes = Integer.parseInt(splitList[1])
        } else {
            hours = calendarObj.get(Calendar.HOUR)
            minutes = calendarObj.get(Calendar.MINUTE)
        }

        TimePickerDialog(activity, { _, h, m ->
            activityEndTime.setText(String.format("%02d:%02d", h, m))
        }, hours, minutes, false)
            .show()
    }

    // Make sure Title, Place and Date fields are not-empty before saving into DB
    private fun validateData(): String? {
        var inputData = activityTitle.text.toString()
        if (inputData.isEmpty()) {
            return Utils.ERR_MSG_TITLE
        }

        inputData = activityDateBut.text.toString()
        if (inputData.isEmpty()) {
            return Utils.ERR_MSG_DATE
        }

        inputData = activityPlace.text.toString()
        if (inputData.isEmpty()) {
            return Utils.ERR_MSG_PLACE
        }

        return null
    }

    // Confirmation Dialog to Update/Delete an Activity
    private fun showConfirmDialog(msg: String, type: Int) {
        val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
        dialogBuilder?.setMessage(msg)
            ?.setCancelable(false)
            ?.setPositiveButton("Proceed") { _, _ ->
                if (type == 1)
                    updateActivity()
                else
                    deleteActivity()
            }
            ?.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

        val confirmDialog = dialogBuilder?.create()
        confirmDialog?.setTitle("Confirm")
        confirmDialog?.show()
    }

    // Create Instance for this Fragment
    companion object {
        @JvmStatic
        fun getInstance() =
            FragmentActivityDetails().apply {
            }
    }

    // Close this Fragment
    private fun closeFragment() {
        this.activity?.supportFragmentManager?.popBackStack()
    }
}