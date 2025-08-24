package app.icampuspass

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppRepository() {
    private val scope: CoroutineScope = CoroutineScope(context = SupervisorJob())

    fun init() {
        scope.launch {}
    }
}
