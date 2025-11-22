package app.icampuspass.viewmodels

import app.icampuspass.models.TkuIlifeAccountRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserPickerScreenViewModel(
    private val tkuIlifeAccountRepository: TkuIlifeAccountRepository
) : ViewModel() {
    @NativeCoroutinesState
    val currentUserId: StateFlow<String?> = tkuIlifeAccountRepository.currentAccountId
        .stateIn(
            scope = viewModelScope.coroutineScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = null
        )

    fun saveCurrentUserId(newId: String) {
        viewModelScope.coroutineScope.launch {
            tkuIlifeAccountRepository.addAccountId(id = newId)
        }
    }

    fun removeCurrentUserId() {
        viewModelScope.coroutineScope.launch {
            tkuIlifeAccountRepository.removeAccountId()
        }
    }
}
