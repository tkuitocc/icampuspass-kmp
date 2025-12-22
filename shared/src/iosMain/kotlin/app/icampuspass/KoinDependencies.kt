package app.icampuspass

import app.icampuspass.models.UserAccountRepository
import app.icampuspass.models.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val userAccountRepository: UserAccountRepository by inject()

    val userRepository: UserRepository by inject()
}
