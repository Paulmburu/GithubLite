package github.paulmburu.repository.repository

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.local.dao.FollowersDao
import github.paulmburu.local.dao.FollowingDao
import github.paulmburu.local.dao.RepoDao
import github.paulmburu.local.dao.UserDao
import github.paulmburu.network.api.GithubApi
import github.paulmburu.repository.util.Data
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GithubRepositoryImplTest {
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setUp() {
        val taskApi = mockk<GithubApi> {
            coEvery {
                fetchUser(Data.query)
            } returns Response.success(Data.userQueryResponse)

            coEvery {
                fetchRepos(Data.query)
            } returns Response.success(Data.reposQueryResponse)

            coEvery {
                fetchFollowers(Data.query)
            } returns Response.success(Data.followersQueryResponse)

            coEvery {
                fetchFollowing(Data.query)
            } returns Response.success(Data.followingQueryResponse)
        }

        val tasksDao = mockk<UserDao> {
            coEvery {
                findUser(search = Data.query)
            } returns flow { listOf(Data.userResponse)}
        }

        val repoDao = mockk<RepoDao> {
            coEvery {
                getRepos()
            } returns flow { Data.reposResponse }
        }

        val followersDao = mockk<FollowersDao> {
            coEvery {
                getFollowers()
            } returns flow { Data.followersResponse }
        }

        val followingDao = mockk<FollowingDao> {
            coEvery {
                getFollowing()
            } returns flow { Data.followingResponse }
        }

        githubRepository = GithubRepositoryImpl(taskApi, tasksDao, repoDao, followersDao, followingDao)
    }


    @Test
    fun `when fetch user is called, returns resource of user response containing user information`() = runBlocking {

        val response = githubRepository.fetchUser(username = Data.query).first()
        Truth.assertThat(response).isInstanceOf(Resource.Success::class.java)
    }
}