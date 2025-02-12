package app.icampuspass.models

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class UserRepository(
    private val greeting: Greeting
) {
    private val scope = CoroutineScope(context = SupervisorJob())

    fun init() {
        scope.launch {}
    }

    fun getGreeting(): Greeting {
        return greeting
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
