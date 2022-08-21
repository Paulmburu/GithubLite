package github.paulmburu.network.models

import com.google.gson.annotations.SerializedName

data class RepoDto(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
)