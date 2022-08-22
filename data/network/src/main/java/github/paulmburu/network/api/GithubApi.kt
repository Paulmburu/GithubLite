package github.paulmburu.network.api

import github.paulmburu.network.models.FollowerDto
import github.paulmburu.network.models.FollowingDto
import github.paulmburu.network.models.RepoDto
import github.paulmburu.network.models.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{user}")
    suspend fun fetchUser(@Path("user")user: String): Response<UserDto>

    @GET("users/{user}/repos")
    suspend fun fetchRepos(@Path("user")user: String): Response<List<RepoDto>>

    @GET("users/{user}/followers")
    suspend fun fetchFollowers(@Path("user")user: String): Response<List<FollowerDto>>

    @GET("users/{user}/following")
    suspend fun fetchFollowing(@Path("user")user: String): Response<List<FollowingDto>>
}