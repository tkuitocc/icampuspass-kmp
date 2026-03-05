package app.icampuspass.shared

import app.icampuspass.shared.models.database.DriverFactory
import app.icampuspass.shared.models.database.IOSDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single<DriverFactory> {
        IOSDriverFactory()
    }
}
