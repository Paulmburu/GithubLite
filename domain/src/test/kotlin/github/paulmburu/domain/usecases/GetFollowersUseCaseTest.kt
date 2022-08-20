package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFollowersUseCaseTest {
    private lateinit var getFollowersUseCaseTest: GetFollowersBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        getFollowersUseCaseTest = GetFollowersUseCase(
            githubRepository
        )
    }

    @Test
    fun `When GetFollowersUseCase is called, Followers should be returned`() =
        runBlocking {
            getFollowersUseCaseTest("Paulmburu").collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        Truth.assertThat(resource.data).isEqualTo(Data.Followers.response)
                    }
                    is Resource.Error -> {
                        Truth.assertThat(resource.data).isNotEqualTo(Data.Followers.response)
                    }
                }
            }
        }
}