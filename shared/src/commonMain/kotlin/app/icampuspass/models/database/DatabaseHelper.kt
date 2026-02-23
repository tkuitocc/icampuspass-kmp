package app.icampuspass.models.database

class DatabaseHelper(
    private val driverFactory: DriverFactory
) {
    companion object {
        const val FILE_NAME = "icampuspass.db"
    }

    private val driver = driverFactory.createDriver()

    private val database = Database(driver)

    private val databaseQueries: DatabaseQueries = database.databaseQueries

    fun selectAllTkuIlifeAccounts(): List<String> {
        return databaseQueries.selectAllTkuIlifeAccounts().executeAsList()
    }

    suspend fun insertTkuIlifeAccount(tiaStudentIdNo: String) {
        databaseQueries.insertTkuIlifeAccount(tia_student_id_no = tiaStudentIdNo)
    }

    suspend fun deleteTkuIlifeAccounts() {
        databaseQueries.deleteTkuIlifeAccounts()
    }
}
