package app.icampuspass

import android.app.Application
import app.icampuspass.viewmodels.GreetingScreenViewModel
import app.icampuspass.viewmodels.MainScreenViewModel
import org.koin.dsl.module

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            androidContext = this@AndroidApp,
            extraModules = listOf(
                module {
                    factory<GreetingScreenViewModel> {
                        GreetingScreenViewModel(
                            userRepository = get()
                        )
                    }

                    factory<MainScreenViewModel> {
                        MainScreenViewModel()
                    }
                }
            )
        )
    }
}
