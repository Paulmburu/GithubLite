package github.paulmburu.domain.models

data class User(
    val id: Int,
    val login: String,
    val name: String,
    val bio: String,
    val avatarUrl: String,
    val company: String?,
    val location: String,
    val repos: Int,
    val followers: Int,
    val following: Int
)