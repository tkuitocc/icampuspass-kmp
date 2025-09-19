package app.icampuspass

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val appRepository: AppRepository by inject()
}
