package app.icampuspass

import app.icampuspass.models.database.DriverFactory
import app.icampuspass.models.database.IOSDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single<DriverFactory> {
        IOSDriverFactory()
    }
}
