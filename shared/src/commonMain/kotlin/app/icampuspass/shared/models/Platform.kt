package app.icampuspass.shared.models

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
