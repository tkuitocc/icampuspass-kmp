package app.icampuspass.shared

import app.icampuspass.shared.models.UserAccountRepository
import app.icampuspass.shared.models.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val userAccountRepository: UserAccountRepository by inject()

    val userRepository: UserRepository by inject()
}
