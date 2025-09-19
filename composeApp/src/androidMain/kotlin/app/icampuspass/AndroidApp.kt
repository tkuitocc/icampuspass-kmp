package app.icampuspass

import android.app.Application
import app.icampuspass.viewmodels.MainViewModel
import org.koin.dsl.module

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            androidContext = this@AndroidApp,
            extraModules = listOf(
                module {
                    factory<MainViewModel> {
                        MainViewModel(appRepository = get())
                    }
                }
            )
        )
    }
}
