package app.icampuspass

import android.app.Application
import org.koin.dsl.module

class AndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            androidContext = this@AndroidApp,
            extraModules = listOf(
                module {}
            )
        )
    }
}
