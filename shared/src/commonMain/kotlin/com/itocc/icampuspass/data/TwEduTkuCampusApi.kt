package com.itocc.icampuspass.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

class TwEduTkuCampusApi(private val client: HttpClient): ICampusApi {
    companion object {
        private const val LOGIN_API_URL =
            "https://sso.tku.edu.tw/ilife/CoWork/AndroidSsoLogin.cshtml"

        private const val LOGOUT_API_URL =
            "https://sso.tku.edu.tw/pkmslogout"

        private const val COURSE_TIMETABLE_API_URL =
            "https://ilifeapi.az.tku.edu.tw/api/ilifeStuClassApi"
    }

    override suspend fun getCourseTimetableData(): List<CampusCourseTimetableObject> {
        return try {
            client.get(COURSE_TIMETABLE_API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }

            e.printStackTrace()

            emptyList()
        }
    }
}