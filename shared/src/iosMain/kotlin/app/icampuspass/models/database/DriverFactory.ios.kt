package app.icampuspass.models.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class IOSDriverFactory() : DriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = Database.Schema.synchronous(),
            name = DatabaseHelper.FILE_NAME
        )
    }
}
