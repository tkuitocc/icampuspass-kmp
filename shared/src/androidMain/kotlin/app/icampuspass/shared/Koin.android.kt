package app.icampuspass.shared

import app.icampuspass.shared.models.database.AndroidDriverFactory
import app.icampuspass.shared.models.database.DriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single<DriverFactory> {
        AndroidDriverFactory(context = get())
    }
}
