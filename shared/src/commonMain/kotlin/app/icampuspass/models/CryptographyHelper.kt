package app.icampuspass.models

import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.DelicateCryptographyApi
import dev.whyoleg.cryptography.algorithms.AES
import kotlinx.io.bytestring.encode
import kotlinx.io.bytestring.encodeToByteString
import kotlin.io.encoding.Base64

object CryptographyHelper {
    val provider: CryptographyProvider = CryptographyProvider.Default

    @OptIn(DelicateCryptographyApi::class)
    suspend fun decodeAesCbc(
        iv: String,
        key: String,
        text: String
    ): String {
        val key = provider.get(AES.CBC).keyDecoder()
            .decodeFromByteString(format = AES.Key.Format.RAW, byteString = key.encodeToByteString())

        val cipher = key.cipher()

        return Base64.encode(
            source = cipher.encryptWithIv(
                iv = iv.encodeToByteString(),
                plaintext = text.encodeToByteString()
            )
        )
    }
}
