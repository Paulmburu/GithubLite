package github.paulmburu.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import github.paulmburu.local.dao.*
import github.paulmburu.local.models.FollowerEntity
import github.paulmburu.local.models.FollowingEntity
import github.paulmburu.local.models.RepoEntity
import github.paulmburu.local.models.UserEntity

@Database(
    entities = [UserEntity::class, RepoEntity::class, FollowerEntity::class, FollowingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repoDao: RepoDao
    abstract val followersDao: FollowersDao
    abstract val followingDao: FollowingDao
}