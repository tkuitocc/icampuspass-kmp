package com.itocc.icampuspass.di

import com.itocc.icampuspass.data.AppRepository
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
    single<AppRepository> {
        AppRepository().apply {
            init()
        }
    }

    single<HttpClient> {
        HttpClient {}
    }
}

fun initKoin() = initKoin(extraModules = emptyList())

fun initKoin(extraModules: List<Module>) {
    startKoin {
        modules(
            dataModule,
            *extraModules.toTypedArray(),
        )
    }
}
