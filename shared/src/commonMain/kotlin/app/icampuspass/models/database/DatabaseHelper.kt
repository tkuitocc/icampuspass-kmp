package app.icampuspass.models.database

class DatabaseHelper(
    private val driverFactory: DriverFactory
) {
    companion object {
        const val FILE_NAME = "icampuspass.db"
    }

    private val driver = driverFactory.createDriver()

    private val database = Database(driver)
}
