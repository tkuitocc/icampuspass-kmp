package com.itocc.icampuspass

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module = module {}

fun initKoin() = initKoin(extraModules = emptyList())

fun initKoin(
    extraModules: List<Module>
) {
    startKoin {
        modules(
            dataModule,
            *extraModules.toTypedArray(),
        )
    }
}
