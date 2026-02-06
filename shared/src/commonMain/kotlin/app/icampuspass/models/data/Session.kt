package app.icampuspass.models.data

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val id: String,
    val courseSession: String,
    val courseName: String,
    val instructorName: String,
    val location: String,
    val gradeNumber: String,
    val examSeatNumber: String?
)
