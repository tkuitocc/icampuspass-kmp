package app.icampuspass.models.database

import app.cash.sqldelight.db.SqlDriver

class DatabaseHelper(
    private val driverFactory: DriverFactory
) {
    companion object {
        const val FILE_NAME = "icampuspass.db"
    }

    private val driver = driverFactory.createDriver()

    private val database: Database = Database(driver)

    private val databaseQueries: DatabaseQueries = database.databaseQueries
}
