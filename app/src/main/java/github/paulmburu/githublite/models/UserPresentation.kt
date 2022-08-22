package github.paulmburu.githublite.models

data class UserPresentation(
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