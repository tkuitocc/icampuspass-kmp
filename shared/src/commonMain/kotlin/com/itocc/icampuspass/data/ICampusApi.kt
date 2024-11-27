package com.itocc.icampuspass.data

interface ICampusApi {
    suspend fun getCourseTimetableData(): List<CampusCourseTimetableObject>
}