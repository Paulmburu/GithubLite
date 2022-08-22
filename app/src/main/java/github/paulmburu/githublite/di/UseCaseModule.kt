package github.paulmburu.githublite.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.*

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    fun providesFetchUserUseCase(githubRepository: GithubRepository): FetchUserUseCase {
        return FetchUserUseCase(githubRepository)
    }

    @Provides
    fun providesGetUserUseCase(githubRepository: GithubRepository): GetUserUseCase {
        return GetUserUseCase(githubRepository)
    }

    @Provides
    fun providesInsertUserUseCase(githubRepository: GithubRepository): InsertUserUseCase {
        return InsertUserUseCase(githubRepository)
    }

    @Provides
    fun providesFetchReposUseCase(githubRepository: GithubRepository): FetchReposUseCase {
        return FetchReposUseCase(githubRepository)
    }

    @Provides
    fun providesGetReposUseCase(githubRepository: GithubRepository): GetReposUseCase {
        return GetReposUseCase(githubRepository)
    }

    @Provides
    fun providesInsertReposUseCase(githubRepository: GithubRepository): InsertReposUseCase {
        return InsertReposUseCase(githubRepository)
    }

    @Provides
    fun providesFetchFollowersUseCase(githubRepository: GithubRepository): FetchFollowersUseCase {
        return FetchFollowersUseCase(githubRepository)
    }

    @Provides
    fun providesGetFollowersUseCase(githubRepository: GithubRepository): GetFollowersUseCase {
        return GetFollowersUseCase(githubRepository)
    }

    @Provides
    fun providesInsertFollowersUseCase(githubRepository: GithubRepository): InsertFollowersUseCase {
        return InsertFollowersUseCase(githubRepository)
    }

    @Provides
    fun providesFetchFollowingUseCase(githubRepository: GithubRepository): FetchFollowingUseCase {
        return FetchFollowingUseCase(githubRepository)
    }

    @Provides
    fun providesGetFollowingUseCase(githubRepository: GithubRepository): GetFollowingUseCase {
        return GetFollowingUseCase(githubRepository)
    }

    @Provides
    fun providesInsertFollowingUseCase(githubRepository: GithubRepository): InsertFollowingUseCase {
        return InsertFollowingUseCase(githubRepository)
    }
}