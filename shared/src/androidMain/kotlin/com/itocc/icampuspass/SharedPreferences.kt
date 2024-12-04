package com.itocc.icampuspass

import android.content.SharedPreferences

private const val CURRENT_CAMPUS_ID = "CURRENT_CAMPUS_ID"

private const val USER_ID = "USER_ID"

fun SharedPreferences.getCurrentCampusId(): String? {
    return this.getString(CURRENT_CAMPUS_ID, null)
}

fun SharedPreferences.saveCurrentCampusId(campusId: String) {
    return this.edit {
        putString(CURRENT_CAMPUS_ID, campusId)
    }
}

fun SharedPreferences.getCurrentUserId(campusId: String): String? {
    return this.getString("${campusId}_$USER_ID", null)
}

fun SharedPreferences.saveCurrentUserId(campusId: String, userId: String) {
    return this.edit {
        putString("${campusId}_$USER_ID", userId)
    }
}
