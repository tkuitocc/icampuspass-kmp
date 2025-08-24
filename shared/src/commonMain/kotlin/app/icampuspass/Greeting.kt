package app.icampuspass

class Greeting(
    private val platform: Platform
) {
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
