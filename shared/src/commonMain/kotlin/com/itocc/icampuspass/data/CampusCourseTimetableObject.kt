package com.itocc.icampuspass.data

import kotlinx.serialization.Serializable

@Serializable
data class CampusCourseTimetableObject (
    val title: String,
    val instructors: String,
    val location: String
)