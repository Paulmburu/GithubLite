package github.paulmburu.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth
import github.paulmburu.local.dao.UserDao
import github.paulmburu.local.database.GithubDatabase
import github.paulmburu.local.mappers.toDomain
import github.paulmburu.local.models.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var database: GithubDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GithubDatabase::class.java
        ).build()

        userDao = database.userDao
    }

    @Test
    fun insertingUser_shouldSave_the_correctData() = runBlocking {
        val users = listOf(
            UserEntity(
                id = 24954467,
                login = "Paulmburu",
                name = "Paul Mburu",
                bio = "Software Engineer || Tech Enthusiast\\r\\n",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
                company = null,
                location = "Nairobi, Kenya",
                repos = 94,
                followers = 134,
                following = 215
            )
        )

        userDao.insertUser(users)

        val response = userDao.findUser("Paulmburu").first()
        Truth.assertThat(response.size).isGreaterThan(0)
        Truth.assertThat(response.first().id).isEqualTo(users.first().id)

    }

    @Test
    fun Find_User_Returns_the_correct_data() = runBlocking {
        val users = listOf(
            UserEntity(
                id = 24954467,
                login = "Paulmburu",
                name = "Paul Mburu",
                bio = "Software Engineer || Tech Enthusiast\\r\\n",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
                company = null,
                location = "Nairobi, Kenya",
                repos = 94,
                followers = 134,
                following = 215
            ),


            )

        userDao.insertUser(users)

        userDao.findUser("Paulmburu").collect {
            val data = it.map { userEntity -> userEntity.toDomain() }
            Truth.assertThat(data.size).isEqualTo(1)
            Truth.assertThat(data.last().id).isEqualTo(users.last().id)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}