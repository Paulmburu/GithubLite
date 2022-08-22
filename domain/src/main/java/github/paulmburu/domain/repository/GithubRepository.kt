package github.paulmburu.domain.repository

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import kotlinx.coroutines.flow.Flow


interface GithubRepository {
    fun fetchUser(username: String): Flow<Resource<User>>
    suspend fun insertUser(user: User)
    fun getUser(username: String): Flow<Resource<User>>

    fun fetchRepos(username: String): Flow<Resource<List<Repo>>>
    suspend fun insertRepos(repos: List<Repo>)
    fun getRepos(username: String): Flow<Resource<List<Repo>>>

    fun fetchFollowers(username: String): Flow<Resource<List<Follower>>>
    suspend fun insertFollowers(followers: List<Follower>)
    fun getFollowers(): Flow<Resource<List<Follower>>>

    fun fetchFollowing(username: String): Flow<Resource<List<Following>>>
    suspend fun insertFollowing(following: List<Following>)
    fun getFollowing(): Flow<Resource<List<Following>>>
}