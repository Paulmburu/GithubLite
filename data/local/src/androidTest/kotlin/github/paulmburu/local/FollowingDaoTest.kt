package github.paulmburu.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth
import github.paulmburu.local.dao.FollowingDao
import github.paulmburu.local.dao.UserDao
import github.paulmburu.local.database.GithubDatabase
import github.paulmburu.local.mappers.toDomain
import github.paulmburu.local.models.FollowerEntity
import github.paulmburu.local.models.FollowingEntity
import github.paulmburu.local.models.UserEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FollowingDaoTest {

    private lateinit var database: GithubDatabase
    private lateinit var followingDao: FollowingDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GithubDatabase::class.java
        ).build()

        followingDao = database.followingDao
    }

    @Test
    fun insertingUser_shouldSave_the_correctData() = runBlocking {
        val following = listOf(
            FollowingEntity(
                id = 24954467,
                login = "Paulmburu",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            )
        )

        followingDao.insertFollowing(following)

        val response = followingDao.getFollowing().first()
        Truth.assertThat(response.size).isGreaterThan(0)
        Truth.assertThat(response.first().id).isEqualTo(following.first().id)

    }

    @Test
    fun Find_User_Returns_the_correct_data() = runBlocking {
        val following = listOf(
            FollowingEntity(
                id = 24954467,
                login = "Paulmburu",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            )
        )

        followingDao.insertFollowing(following)

        followingDao.getFollowing().collect {
            val data = it.map { followingEnity -> followingEnity.toDomain() }
            Truth.assertThat(data.size).isEqualTo(1)
            Truth.assertThat(data.last().id).isEqualTo(following.last().id)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}