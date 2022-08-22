package github.paulmburu.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.paulmburu.local.dao.*
import github.paulmburu.local.database.GithubDatabase


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesProductsDatabase(@ApplicationContext context: Context): GithubDatabase {
        return Room.databaseBuilder(
            context,
            GithubDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesUserDao(database: GithubDatabase): UserDao {
        return database.userDao
    }

    @Provides
    fun providesReposDao(database: GithubDatabase): ReposDao {
        return database.reposDao
    }

    @Provides
    fun providesFollowersDao(database: GithubDatabase): FollowersDao {
        return database.followersDao
    }

    @Provides
    fun providesFollowingDao(database: GithubDatabase): FollowingDao {
        return database.followingDao
    }

    const val DATABASE_NAME = "products_database"
}