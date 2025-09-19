package app.icampuspass

import app.icampuspass.models.database.DatabaseHelper
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    single<AppRepository> {
        AppRepository().apply {
            init()
        }
    }

    single<DatabaseHelper> {
        DatabaseHelper(driverFactory = get())
    }
}

expect val platformModule: Module

fun initKoin(
    extraModules: List<Module>
) {
    startKoin {
        modules(
            commonModule,
            platformModule,
            *extraModules.toTypedArray(),
        )
    }
}

fun initKoin() = initKoin(extraModules = emptyList())
