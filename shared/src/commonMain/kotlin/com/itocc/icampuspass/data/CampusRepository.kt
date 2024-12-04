package com.itocc.icampuspass.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import com.itocc.icampuspass.data.ICampusModules
import com.itocc.icampuspass.data.ICampusUsers

class CampusRepository(
    private val campusUsers: ICampusUsers,
    private val campusModules: ICampusModules,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
        }
    }
}
