package app.icampuspass.viewmodels

import app.icampuspass.models.TkuIlifeAccountRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClassScheduleScreenViewModel(
    private val tkuIlifeAccountRepository: TkuIlifeAccountRepository
) : ViewModel() {
    @NativeCoroutinesState
    val currentUserId: StateFlow<String?> = tkuIlifeAccountRepository.currentAccountId
        .stateIn(
            scope = viewModelScope.coroutineScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = null
        )

    @NativeCoroutinesState
    val currentUserClassSchedule: StateFlow<Map<String, Any?>?> =
        tkuIlifeAccountRepository.currentAccountClassSchedule.stateIn(
            scope = viewModelScope.coroutineScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = null
        )

    @NativeCoroutinesState
    val isRefreshingClassSchedule: StateFlow<Boolean> =
        tkuIlifeAccountRepository.isRefreshingClassSchedule.stateIn(
            scope = viewModelScope.coroutineScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = true
        )

    fun refreshClassSchedule() {
        viewModelScope.coroutineScope.launch {
            tkuIlifeAccountRepository.refreshAccountClassSchedule()
        }
    }
}
