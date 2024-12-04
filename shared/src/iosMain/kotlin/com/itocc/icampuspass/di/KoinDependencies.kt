package com.itocc.icampuspass.di

import com.itocc.icampuspass.data.CampusRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies: KoinComponent {
    val campusRepository: CampusRepository by inject()
}
