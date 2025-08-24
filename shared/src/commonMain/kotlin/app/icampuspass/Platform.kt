package app.icampuspass

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
