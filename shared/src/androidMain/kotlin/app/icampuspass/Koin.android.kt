package app.icampuspass

import android.content.Context
import app.icampuspass.models.database.AndroidDriverFactory
import app.icampuspass.models.database.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    single<DriverFactory> {
        AndroidDriverFactory(context = get())
    }
}

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
