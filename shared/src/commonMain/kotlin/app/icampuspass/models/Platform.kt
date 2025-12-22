package app.icampuspass.models

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
