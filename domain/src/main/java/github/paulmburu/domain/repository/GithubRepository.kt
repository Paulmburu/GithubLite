package github.paulmburu.domain.repository

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.User
import kotlinx.coroutines.flow.Flow


interface GithubRepository {
    fun fetchUser(username: String): Flow<Resource<User>>
    suspend fun insertUser(user: User)
    fun getUser(): Flow<Resource<User>>

    fun fetchFollowers(username: String): Flow<Resource<List<Follower>>>
    suspend fun insertFollowers(data: List<Follower>)
    fun getFollowers(): Flow<Resource<List<Follower>>>

    fun fetchFollowing(username: String): Flow<Resource<List<Follower>>>
    suspend fun insertFollowing(data: List<Follower>)
    fun getFollowing(): Flow<Resource<List<Follower>>>
}