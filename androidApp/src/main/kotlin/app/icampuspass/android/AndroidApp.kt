package app.icampuspass.android

import android.app.Application
import app.icampuspass.shared.initKoinWith
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoinWith(
            configurations = {
                androidContext(androidContext = this@AndroidApp)
            }
        )
    }
}
