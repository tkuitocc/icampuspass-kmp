package app.icampuspass

import app.icampuspass.models.database.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DriverFactory> {
        DriverFactory()
    }
}
