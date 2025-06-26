package com.itocc.icampuspass

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {}

fun initKoin(
    androidContext: Context,
    extraModules: List<Module> = emptyList()
) {
    startKoin {
        androidContext(androidContext = androidContext)

        modules(
            commonModule,
            platformModule,
            *extraModules.toTypedArray(),
        )
    }
}
