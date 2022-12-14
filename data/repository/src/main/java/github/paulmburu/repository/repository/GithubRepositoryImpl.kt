package github.paulmburu.repository.repository

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.local.dao.*
import github.paulmburu.local.mappers.toDomain
import github.paulmburu.local.mappers.toLocal
import github.paulmburu.network.api.GithubApi
import github.paulmburu.network.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val userDao: UserDao,
    private val reposDao: ReposDao,
    private val followersDao: FollowersDao,
    private val followingDao: FollowingDao
) : GithubRepository {
    override fun fetchUser(username: String): Flow<Resource<User>> = flow {
        try {
            val result = githubApi.fetchUser(user = username)
            print(result)

            when {
                result.isSuccessful -> {
                    emit(
                        Resource.Success(result.body()!!.toDomain())
                    )
                    userDao.insertUser(
                        arrayListOf(result.body()!!.toDomain().toLocal())
                    )
                }
                else -> emit(Resource.Error(message = result.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(users = arrayListOf(user.toLocal()))
    }

    override fun getUser(username: String): Flow<Resource<User>> = flow {
        try {
            userDao.findUser(search = username).collect {
                val data = it.map { userEntity -> userEntity.toDomain() }
                emit(
                    Resource.Success(
                        data.first()
                    )
                )
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun fetchRepos(username: String): Flow<Resource<List<Repo>>> = flow{
        try {
            val result = githubApi.fetchRepos(user = username)

            when {
                result.isSuccessful -> {
                    emit(
                        Resource.Success(
                            result.body()!!.map { repoDto -> repoDto.toDomain() })
                    )
                    reposDao.insertRepos(
                        result.body()!!.map { repoDto -> repoDto.toDomain().toLocal() }
                    )
                }
                else -> emit(Resource.Error(message = result.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun insertRepos(repos: List<Repo>) {
        reposDao.insertRepos(repos = repos.map { repo -> repo.toLocal() })
    }

    override fun getRepos(username: String): Flow<Resource<List<Repo>>> = flow{
        try {
            reposDao.getRepos().collect {
                val data = it.map { repoEntity -> repoEntity.toDomain() }
                emit(
                    Resource.Success(
                        data
                    )
                )
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun fetchFollowers(username: String): Flow<Resource<List<Follower>>> = flow {
        try {
            val result = githubApi.fetchFollowers(user = username)

            when {
                result.isSuccessful -> {
                    emit(
                        Resource.Success(
                            result.body()!!.map { followerDto -> followerDto.toDomain() })
                    )
                    followersDao.insertFollowers(
                        result.body()!!.map { followerDto -> followerDto.toDomain().toLocal() }
                    )
                }
                else -> emit(Resource.Error(message = result.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun insertFollowers(followers: List<Follower>) {
        followersDao.insertFollowers(followers = followers.map { follower -> follower.toLocal() })
    }

    override fun getFollowers(): Flow<Resource<List<Follower>>> = flow {
        try {
            followersDao.getFollowers().collect {
                val data = it.map { userEntity -> userEntity.toDomain() }
                emit(
                    Resource.Success(
                        data
                    )
                )
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun fetchFollowing(username: String): Flow<Resource<List<Following>>> = flow {
        try {
            val result = githubApi.fetchFollowing(user = username)

            when {
                result.isSuccessful -> {
                    emit(
                        Resource.Success(
                            result.body()!!.map { followerDto -> followerDto.toDomain() })
                    )
                    followingDao.insertFollowing(
                        result.body()!!.map { followingDto -> followingDto.toDomain().toLocal() }
                    )
                }
                else -> emit(Resource.Error(message = result.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun insertFollowing(following: List<Following>) {
        followingDao.insertFollowing(following = following.map { it -> it.toLocal() })
    }

    override fun getFollowing(): Flow<Resource<List<Following>>> = flow {
        try {
            followingDao.getFollowing().collect {
                val data = it.map { followingEntity -> followingEntity.toDomain() }
                emit(
                    Resource.Success(
                        data
                    )
                )
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

}