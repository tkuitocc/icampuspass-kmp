package app.icampuspass

import app.icampuspass.models.database.DriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single<DriverFactory> {
        DriverFactory()
    }
}
