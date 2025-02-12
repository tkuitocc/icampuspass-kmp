package com.itocc.icampuspass

import android.app.Application
import com.itocc.icampuspass.viewmodels.MainViewModel
import org.koin.dsl.module

class AndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            androidContext = this@AndroidApp,
            extraModules = listOf(
                module {
                    factory<MainViewModel> {
                        MainViewModel(repository = get())
                    }
                }
            )
        )
    }
}
