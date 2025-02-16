package app.skeleton

interface Platform {
    val name: String

    val version: String
    val language: String
    val type: Type

    enum class Type {
        ANDROID,
        IOS,
    }
}