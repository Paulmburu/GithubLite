package github.paulmburu.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth
import github.paulmburu.local.dao.FollowersDao
import github.paulmburu.local.database.GithubDatabase
import github.paulmburu.local.mappers.toDomain
import github.paulmburu.local.models.FollowerEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FollowersDaoTest {

    private lateinit var database: GithubDatabase
    private lateinit var followersDao: FollowersDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GithubDatabase::class.java
        ).build()

        followersDao = database.followersDao
    }

    @Test
    fun insertingFollowers_shouldSave_the_correctData() = runBlocking {
        val users = listOf(
            FollowerEntity(
                id = 24954467,
                login = "Paulmburu",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            )
        )

        followersDao.insertFollowers(users)

        val response = followersDao.getFollowers().first()
        Truth.assertThat(response.size).isGreaterThan(0)
        Truth.assertThat(response.first().id).isEqualTo(users.first().id)

    }

    @Test
    fun Get_Followers_Returns_the_correct_data() = runBlocking {
        val followers = listOf(
            FollowerEntity(
                id = 24954467,
                login = "Paulmburu",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            )
            )

        followersDao.insertFollowers(followers)

        followersDao.getFollowers().collect {
            val data = it.map { followerEntity -> followerEntity.toDomain() }
            Truth.assertThat(data.size).isEqualTo(1)
            Truth.assertThat(data.last().id).isEqualTo(followers.last().id)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}