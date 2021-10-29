package com.usc.workoutapp.utils

/*
* POJO class which defines constants used through out this APP
*
* */
class Utils {
    companion object {
        const val ACTIVITY_POS: String = "act_pos"
        const val DEFAULT_POS: Int = -2
        const val NEW_POS: Int = -1

        const val ALERT_UPDATE: Int = 1
        const val ALERT_DELETE: Int = 2

        const val MSG_UPDATE: String = "Update Current Activity ?"
        const val MSG_DELETE: String = "Delete Current Activity ?"
        const val MSG_INSERT: String = "Activity Inserted into DB !!"

        const val RG_TAG_INDIVIDUAL = "Individual"
        const val RG_TAG_GROUP = "Group"

        const val DATE_PATTERN = "dd/MM/yyyy"

        const val ERR_MSG_TITLE = "Title Missing"
        const val ERR_MSG_DATE = "Date Missing"
        const val ERR_MSG_PLACE = "Place Missing"
    }
}