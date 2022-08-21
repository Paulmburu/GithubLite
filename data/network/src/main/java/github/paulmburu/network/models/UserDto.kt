package github.paulmburu.network.models

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,
    val login: String,
    val name: String,
    val bio: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val company: String?,
    val location: String,
    @SerializedName("public_repos")
    val repos: Int,
    val followers: Int,
    val following: Int
)