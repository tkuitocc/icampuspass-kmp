package app.icampuspass.shared

import app.icampuspass.shared.models.HttpHelper
import app.icampuspass.shared.models.TkuIlifeAccountRepository
import app.icampuspass.shared.models.UserAccountRepository
import app.icampuspass.shared.models.UserRepository
import app.icampuspass.shared.models.database.DatabaseHelper
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    single<DatabaseHelper> {
        DatabaseHelper(
            driverFactory = get()
        )
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

    single<HttpHelper> {
        HttpHelper(
            httpClient = get()
        )
    }

    single<TkuIlifeAccountRepository> {
        TkuIlifeAccountRepository(
            databaseHelper = get(),
            httpHelper = get()
        ).apply {
            init()
        }
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
