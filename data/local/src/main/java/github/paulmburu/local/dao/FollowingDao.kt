package github.paulmburu.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.paulmburu.local.models.FollowingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowing(following: List<FollowingEntity>)

    @Query("SELECT * FROM following_table")
    fun getFollowing(): Flow<List<FollowingEntity>>
}