package app.icampuspass.models

import app.icampuspass.models.database.DatabaseHelper
import io.ktor.http.encodeURLParameter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TkuIlifeAccountRepository(
    private val databaseHelper: DatabaseHelper,
    private val httpHelper: HttpHelper
) {
    companion object {
        const val AES_CBC_IV = ""

        const val AES_CBC_KEY = ""

        const val AES_CBC_TEXT_PREFIX = ""

        const val STUDENT_CLASS_SCHEDULE_API = "https://ilifeapi.az.tku.edu.tw/api/ilifeStuClassApi"

        const val TEACHER_TEACHING_SCHEDULE_API = "https://ilifeapi.az.tku.edu.tw/api/ilifeTeaClassApi"
    }

    private val scope = CoroutineScope(context = SupervisorJob())

    val isEncryptionSecretsMissing =
        AES_CBC_IV.isBlank() || AES_CBC_IV.isBlank() || AES_CBC_TEXT_PREFIX.isBlank()

    val currentAccountId = MutableStateFlow<String?>(value = null)

    val currentAccountClassSchedule = MutableStateFlow<Map<String, Any?>?>(value = null)

    val isRefreshingClassSchedule = MutableStateFlow(value = false)

    fun init() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        currentAccountId.value = getAccountId()
        refreshAccountClassSchedule()
    }

    fun getAccountId(): String? {
        val accountsList = databaseHelper.selectAllTkuIlifeAccounts()

        if (accountsList.isNotEmpty()) {
            return accountsList.get(index = 0)
        }

        return null
    }

    suspend fun addAccountId(id: String) {
        databaseHelper.insertTkuIlifeAccount(tiaStudentIdNo = id)
        refresh()
    }

    suspend fun removeAccountId() {
        databaseHelper.deleteTkuIlifeAccounts()
        refresh()
    }

    suspend fun refreshAccountClassSchedule() {
        if (isEncryptionSecretsMissing || currentAccountId.value.isNullOrBlank()) {
            currentAccountClassSchedule.value = null

            return
        }

        isRefreshingClassSchedule.value = true

        val token = CryptographyHelper.decodeAesCbc(
            iv = AES_CBC_IV,
            key = AES_CBC_KEY,
            text = "${AES_CBC_TEXT_PREFIX}${currentAccountId.value}"
        )

        val tokenUrlEncoded = token.encodeURLParameter()

        currentAccountClassSchedule.value =
            httpHelper.getJsonAsMap(url = "${STUDENT_CLASS_SCHEDULE_API}?q=${tokenUrlEncoded}")

        isRefreshingClassSchedule.value = false
    }

    suspend fun refreshAccountTeachingSchedule() {
        httpHelper.getJsonAsMap(url = "$TEACHER_TEACHING_SCHEDULE_API?q=")
    }
}
