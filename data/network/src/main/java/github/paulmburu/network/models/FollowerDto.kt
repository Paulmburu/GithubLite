package github.paulmburu.network.models

import com.google.gson.annotations.SerializedName

data class FollowerDto(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)