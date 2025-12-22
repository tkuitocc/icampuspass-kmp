package app.icampuspass.models

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class UserRepository() {
    private val scope = CoroutineScope(context = SupervisorJob())

    fun init() {
        scope.launch {}
    }
}
