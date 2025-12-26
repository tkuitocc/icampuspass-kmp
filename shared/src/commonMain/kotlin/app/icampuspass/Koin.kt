package app.icampuspass

import app.icampuspass.models.Greeting
import app.icampuspass.models.Platform
import app.icampuspass.models.UserAccountRepository
import app.icampuspass.models.UserRepository
import app.icampuspass.models.database.DatabaseHelper
import app.icampuspass.models.getPlatform
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
        DatabaseHelper(driverFactory = get())
    }

    single<Greeting> {
        Greeting(platform = get())
    }

    single<HttpClient> {
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(
                    json = get(),
                    contentType = ContentType.Any
                )
            }

            install(plugin = HttpCookies)
        }
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single<Platform> {
        getPlatform()
    }

    single<UserAccountRepository> {
        UserAccountRepository().apply {
            init()
        }
    }

    single<UserRepository> {
        UserRepository(
            greeting = get()
        ).apply {
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
