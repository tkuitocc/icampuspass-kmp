package app.icampuspass

import app.icampuspass.models.UserAccountRepository
import app.icampuspass.models.UserRepository
import app.icampuspass.models.database.DatabaseHelper
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    single<DatabaseHelper> {
        DatabaseHelper(driverFactory = get())
    }

    single<UserAccountRepository> {
        UserAccountRepository().apply {
            init()
        }
    }

    single<UserRepository> {
        UserRepository().apply {
            init()
        }
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
