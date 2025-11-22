package app.icampuspass.android

import android.app.Application
import app.icampuspass.shared.initKoin
import app.icampuspass.shared.viewmodels.ClassScheduleScreenViewModel
import app.icampuspass.shared.viewmodels.GreetingScreenViewModel
import app.icampuspass.shared.viewmodels.MainScreenViewModel
import app.icampuspass.shared.viewmodels.UserPickerScreenViewModel
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
