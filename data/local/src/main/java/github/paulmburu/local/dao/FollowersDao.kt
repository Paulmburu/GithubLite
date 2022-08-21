package github.paulmburu.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.paulmburu.local.models.FollowerEntity
import github.paulmburu.local.models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowers(followers: List<FollowerEntity>)

    @Query("SELECT * FROM followers_table")
    fun getFollowers(): Flow<List<FollowerEntity>>
}