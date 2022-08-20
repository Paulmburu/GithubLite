package github.paulmburu.network.api

import com.google.common.truth.Truth
import github.paulmburu.network.base.BaseTest
import github.paulmburu.network.models.FollowerDto
import github.paulmburu.network.models.FollowingDto
import github.paulmburu.network.models.RepoDto
import github.paulmburu.network.models.UserDto
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GithubApiTest : BaseTest(){
    @Test
    fun `When fetchUser is called with all status, the correct result should be parsed`() =
        runBlocking {
            val response = githubApi.fetchUser("Paulmburu")
            if (response.isSuccessful) {
                Truth.assertThat(response.body()!!).isInstanceOf(UserDto::class.java)
            }
        }

    @Test
    fun `When fetchRepos is called with all status, the correct result should be parsed`() =
        runBlocking {
            val response = githubApi.fetchRepos("Paulmburu")
            if (response.isSuccessful) {
                Truth.assertThat(response.body()!!.first()).isInstanceOf(RepoDto::class.java)
            }
        }

    @Test
    fun `When fetchFollowers is called with all status, the correct result should be parsed`() =
        runBlocking {
            val response = githubApi.fetchFollowers("Paulmburu")
            if (response.isSuccessful) {
                Truth.assertThat(response.body()!!.first()).isInstanceOf(FollowerDto::class.java)
            }
        }

    @Test
    fun `When fetchFollowing is called with all status, the correct result should be parsed`() =
        runBlocking {
            val response = githubApi.fetchFollowing("Paulmburu")
            if (response.isSuccessful) {
                Truth.assertThat(response.body()!!.first()).isInstanceOf(FollowingDto::class.java)
            }
        }
}
