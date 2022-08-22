package github.paulmburu.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import github.paulmburu.local.dao.FollowersDao
import github.paulmburu.local.dao.FollowingDao
import github.paulmburu.local.dao.ReposDao
import github.paulmburu.local.dao.UserDao
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
    abstract val reposDao: ReposDao
    abstract val followersDao: FollowersDao
    abstract val followingDao: FollowingDao
}