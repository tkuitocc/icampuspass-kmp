package app.icampuspass.shared.models

class Greeting(
    private val platform: Platform
) {
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
