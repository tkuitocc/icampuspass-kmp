package com.itocc.icampuspass.modals

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppRepository {
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

    fun getSchedules() {}

    fun getScheduleById(id: Int) {}

    fun getCurrentSchedule() {}
}
