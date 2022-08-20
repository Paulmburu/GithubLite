package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchFollowersUseCaseTest {
    private lateinit var fetchFollowersUseCaseTest: FetchFollowersBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        fetchFollowersUseCaseTest = FetchFollowersUseCase(
            githubRepository
        )
    }

    @Test
    fun `When FetchFollowersUseCase is called, Followers should be returned`() =
        runBlocking {
            fetchFollowersUseCaseTest("Paulmburu").collect { resource ->
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