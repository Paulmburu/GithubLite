package github.paulmburu.domain.fakes

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class GithubRepositoryFake : GithubRepository {
    private val userDatabase = LinkedHashMap<Int, User>()
    private val followersDatabase = LinkedHashMap<Int, Follower>()
    private val followingDatabase = LinkedHashMap<Int, Follower>()
    private val reposDatabase = LinkedHashMap<Int, Repo>()

    override fun fetchUser(username: String): Flow<Resource<User>> = flow{
        emit(Resource.Success(Data.user))
    }

    override suspend fun insertUser(user: User) {
        userDatabase[user.id] = user
    }

    override fun getUser(): Flow<Resource<User>> {
        return flowOf(Resource.Success(Data.user))
    }

    override fun fetchFollowers(username: String): Flow<Resource<List<Follower>>> = flow {
        emit(Resource.Success(Data.Followers.response))
    }

    override suspend fun insertFollowers(data: List<Follower>) {
        data.forEach { follower ->
            followersDatabase[follower.id] = follower
        }
    }

    override fun getFollowers(): Flow<Resource<List<Follower>>> {
        return flowOf(Resource.Success(Data.Followers.response))
    }

    override fun fetchFollowing(username: String): Flow<Resource<List<Follower>>> = flow {
        emit(Resource.Success(Data.Following.response))
    }

    override suspend fun insertFollowing(data: List<Follower>) {
        data.forEach { following ->
            followingDatabase[following.id] = following
        }
    }

    override fun getFollowing(): Flow<Resource<List<Follower>>> {
        return flowOf(Resource.Success(Data.Following.response))
    }
}