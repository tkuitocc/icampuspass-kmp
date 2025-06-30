package com.itocc.icampuspass

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppRepository(
    val greeting: Greeting
) {
    private val scope: CoroutineScope = CoroutineScope(context = SupervisorJob())

    fun init() {
        scope.launch {}
    }

    fun getEducationUnits() {}

    fun getEducationUnitById(id: Int) {}

    fun getCurrentEducationUnit() {}

    fun getUsers() {}

    fun getUserById(id: Int) {}

    fun getCurrentUser() {}

    fun getAccounts() {}

    fun getAccountById(id: Int) {}

    fun getCurrentAccounts() {}

    fun getClassSchedules() {}

    fun getClassScheduleById(id: Int) {}

    fun getCurrentClassSchedule() {}
}
