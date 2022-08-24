package github.paulmburu.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.local.dao.FollowersDao
import github.paulmburu.local.dao.FollowingDao
import github.paulmburu.local.dao.ReposDao
import github.paulmburu.local.dao.UserDao
import github.paulmburu.network.api.GithubApi
import github.paulmburu.repository.repository.GithubRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesProductsRepository(
        githubApi: GithubApi,
        userDao: UserDao,
        reposDao: ReposDao,
        followersDao: FollowersDao,
        followingDao: FollowingDao
    ): GithubRepository {
        return GithubRepositoryImpl(githubApi, userDao, reposDao, followersDao, followingDao)
    }
}