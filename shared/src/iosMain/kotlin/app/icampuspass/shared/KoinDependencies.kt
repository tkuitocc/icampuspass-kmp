package app.icampuspass.shared

import app.icampuspass.shared.models.UserAccountRepository
import app.icampuspass.shared.models.UserRepository
import app.icampuspass.shared.viewmodels.GreetingScreenViewModel
import app.icampuspass.shared.viewmodels.MainScreenViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object KoinDependencies : KoinComponent {
    val greetingScreenViewModel: GreetingScreenViewModel
        get() = get()

    val mainScreenViewModel: MainScreenViewModel
        get() = get()

    val userAccountRepository: UserAccountRepository
        get() = get()

    val userRepository: UserRepository
        get() = get()
}
