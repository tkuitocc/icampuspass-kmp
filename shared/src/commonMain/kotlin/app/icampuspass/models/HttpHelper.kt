package app.icampuspass.models

import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonObject

class HttpHelper(
    private val httpClient: HttpClient
) {
    suspend fun getJsonAsMap(url: String): Map<String, Any?>? {
        return try {
            httpClient.get(urlString = url).body<JsonObject>()
        } catch (_: NoTransformationFoundException) {
            null
        } catch (_: SerializationException) {
            null
        } catch (_: Throwable) {
            null
        }
    }
}
