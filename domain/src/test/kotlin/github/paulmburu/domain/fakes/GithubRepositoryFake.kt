package github.paulmburu.domain.fakes

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class GithubRepositoryFake : GithubRepository {
    private val userDatabase = LinkedHashMap<Int, User>()
    private val followersDatabase = LinkedHashMap<Int, Follower>()
    private val followingDatabase = LinkedHashMap<Int, Following>()
    private val reposDatabase = LinkedHashMap<Int, Repo>()

    override fun fetchUser(username: String): Flow<Resource<User>> = flow {
        emit(Resource.Success(Data.user))
    }

    override suspend fun insertUser(user: User) {
        userDatabase[user.id] = user
    }

    override fun getUser(username: String): Flow<Resource<User>> {
        return flowOf(Resource.Success(Data.user))
    }

    override fun fetchRepos(username: String): Flow<Resource<List<Repo>>> = flow {
        emit(Resource.Success(Data.Repos.response))
    }

    override suspend fun insertRepos(repos: List<Repo>) {
        repos.forEach { repo ->
            reposDatabase[repo.id] = repo
        }
    }

    override fun getRepos(username: String): Flow<Resource<List<Repo>>> {
        return flowOf(Resource.Success(Data.Repos.response))
    }


    override fun fetchFollowers(username: String): Flow<Resource<List<Follower>>> = flow {
        emit(Resource.Success(Data.Followers.response))
    }

    override suspend fun insertFollowers(followers: List<Follower>) {
        followers.forEach { follower ->
            followersDatabase[follower.id] = follower
        }
    }

    override fun getFollowers(): Flow<Resource<List<Follower>>> {
        return flowOf(Resource.Success(Data.Followers.response))
    }

    override fun fetchFollowing(username: String): Flow<Resource<List<Following>>> = flow {
        emit(Resource.Success(Data.FollowingData.response))
    }

    override suspend fun insertFollowing(following: List<Following>) {
        following.forEach { it ->
            followingDatabase[it.id] = it
        }
    }

    override fun getFollowing(): Flow<Resource<List<Following>>> {
        return flowOf(Resource.Success(Data.FollowingData.response))
    }
}