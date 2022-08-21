package github.paulmburu.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth
import github.paulmburu.local.dao.RepoDao
import github.paulmburu.local.database.GithubDatabase
import github.paulmburu.local.mappers.toDomain
import github.paulmburu.local.models.RepoEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class RepoDaoTest {

    private lateinit var database: GithubDatabase
    private lateinit var repoDao: RepoDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GithubDatabase::class.java
        ).build()

        repoDao = database.repoDao
    }

    @Test
    fun insertingUser_shouldSave_the_correctData() = runBlocking {
        val repos = listOf(
            RepoEntity(
                id = 227655605,
                name = "30-Days-of-Code-1",
                description = "30 Days of Code by HackerRank Solutions in C++, C#, F#, Go, Java, Python, Ruby, Swift & TypeScript. PRs Welcome!",
                stargazersCount = 1
            ),
            RepoEntity(
                id = 227655606,
                name = "algs4",
                description = "Algorithms, 4th edition textbook code and libraries",
                stargazersCount = 4
            ),
        )

        repoDao.insertRepos(repos)

        val response = repoDao.getRepos().first()
        Truth.assertThat(response.size).isGreaterThan(1)
        Truth.assertThat(response.first().id).isEqualTo(repos.first().id)

    }

    @Test
    fun Get_Repo_Returns_the_correct_data() = runBlocking {
        val repos = listOf(
            RepoEntity(
                id = 227655605,
                name = "30-Days-of-Code-1",
                description = "30 Days of Code by HackerRank Solutions in C++, C#, F#, Go, Java, Python, Ruby, Swift & TypeScript. PRs Welcome!",
                stargazersCount = 1
            ),
            RepoEntity(
                id = 227655606,
                name = "algs4",
                description = "Algorithms, 4th edition textbook code and libraries",
                stargazersCount = 4
            ),

            )

        repoDao.insertRepos(repos)

        repoDao.getRepos().collect {
            val data = it.map { repoEntity -> repoEntity.toDomain() }
            Truth.assertThat(data.size).isEqualTo(2)
            Truth.assertThat(data.last().id).isEqualTo(repos.last().id)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}