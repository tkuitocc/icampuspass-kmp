package com.itocc.icampuspass

import android.app.Application
import org.koin.dsl.module

class AndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            extraModules = listOf(
                module {}
            )
        )
    }
}
