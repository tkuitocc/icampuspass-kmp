package app.icampuspass

import android.app.Application
import app.icampuspass.viewmodels.ClassScheduleScreenViewModel
import app.icampuspass.viewmodels.GreetingScreenViewModel
import app.icampuspass.viewmodels.MainScreenViewModel
import app.icampuspass.viewmodels.UserPickerScreenViewModel
import org.koin.dsl.module

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            androidContext = this@AndroidApp,
            extraModules = listOf(
                module {
                    factory<ClassScheduleScreenViewModel> {
                        ClassScheduleScreenViewModel(
                            tkuIlifeAccountRepository = get()
                        )
                    }

                    factory<GreetingScreenViewModel> {
                        GreetingScreenViewModel()
                    }

                    factory<MainScreenViewModel> {
                        MainScreenViewModel()
                    }

                    factory<UserPickerScreenViewModel> {
                        UserPickerScreenViewModel(
                            tkuIlifeAccountRepository = get()
                        )
                    }
                }
            )
        )
    }
}
