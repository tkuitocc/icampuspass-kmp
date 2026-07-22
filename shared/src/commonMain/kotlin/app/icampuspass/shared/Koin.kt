package app.icampuspass.shared

import app.icampuspass.shared.models.UserAccountRepository
import app.icampuspass.shared.models.UserRepository
import app.icampuspass.shared.models.database.DatabaseHelper
import app.icampuspass.shared.viewmodels.GreetingScreenViewModel
import app.icampuspass.shared.viewmodels.MainScreenViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.includes
import org.koin.dsl.module

val sharedModule = module {
    single<DatabaseHelper> {
        DatabaseHelper(driverFactory = get())
    }

    factory<GreetingScreenViewModel> {
        GreetingScreenViewModel()
    }

    single<HttpClient> {
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Any
                )
            }

            install(plugin = HttpCookies)
        }
    }

    factory<MainScreenViewModel> {
        MainScreenViewModel()
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

val commonModule = module {
    includes(
        sharedModule,
        platformModule
    )
}

fun initKoinWith(
    configurations: (KoinApplication.() -> Unit)?
) {
    startKoin {
        includes(configurations)

        modules(commonModule)
    }
}

// Used by iosApp
@Suppress("unused")
fun initKoin() {
    initKoinWith(
        configurations = null
    )
}
