package github.paulmburu.repository.repository

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.local.dao.FollowersDao
import github.paulmburu.local.dao.FollowingDao
import github.paulmburu.local.dao.ReposDao
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
        val githubApi = mockk<GithubApi> {
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

        val userDao = mockk<UserDao> {
            coEvery {
                findUser(search = Data.query)
            } returns flow { listOf(Data.userResponse) }
        }

        val reposDao = mockk<ReposDao> {
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

        githubRepository =
            GithubRepositoryImpl(githubApi, userDao, reposDao, followersDao, followingDao)
    }


    @Test
    fun `when fetch user is called, returns resource of user response containing user information`() =
        runBlocking {
            val response = githubRepository.fetchUser(username = Data.query).first()
            Truth.assertThat(response).isInstanceOf(Resource.Success::class.java)
        }

    @Test
    fun `when fetch repos is called, returns resource of repo response containing repositories information`() =
        runBlocking {
            val response = githubRepository.fetchRepos(username = Data.query).first()
            Truth.assertThat(response).isInstanceOf(Resource.Success::class.java)
        }

    @Test
    fun `when fetch followers is called, returns resource of follower response containing followers information`() =
        runBlocking {
            val response = githubRepository.fetchFollowers(username = Data.query).first()
            Truth.assertThat(response).isInstanceOf(Resource.Success::class.java)
        }

    @Test
    fun `when fetch following is called, returns resource of following response containing following information`() =
        runBlocking {
            val response = githubRepository.fetchFollowing(username = Data.query).first()
            Truth.assertThat(response).isInstanceOf(Resource.Success::class.java)
        }

}