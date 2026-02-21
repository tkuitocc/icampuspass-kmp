package app.icampuspass.viewmodels

import app.icampuspass.models.UserRepository
import com.rickclephas.kmp.observableviewmodel.ViewModel

class ClassScheduleViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    fun getClassScheduleSessions() {}
}
