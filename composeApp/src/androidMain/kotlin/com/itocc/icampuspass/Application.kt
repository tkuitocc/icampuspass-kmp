package com.itocc.icampuspass

import android.app.Application
import com.itocc.icampuspass.di.initKoin
import org.koin.dsl.module

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            listOf(
                module {
                }
            )
        )
    }
}
