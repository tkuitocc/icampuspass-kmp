package app.icampuspass.shared.viewmodels

import app.icampuspass.shared.models.UserRepository
import com.rickclephas.kmp.observableviewmodel.ViewModel

class ClassScheduleViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    fun getClassScheduleSessions() {}
}
