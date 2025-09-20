package app.icampuspass.models.data

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val account: Account
)
