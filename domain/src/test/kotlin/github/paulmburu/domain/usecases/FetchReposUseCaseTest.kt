package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchReposUseCaseTest {
    private lateinit var fetchReposUseCaseTest: FetchReposBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        fetchReposUseCaseTest = FetchReposUseCase(
            githubRepository
        )
    }

    @Test
    fun `When FetchFollowersUseCase is called, Followers should be returned`() =
        runBlocking {
            fetchReposUseCaseTest("Paulmburu").collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        Truth.assertThat(resource.data).isEqualTo(Data.Repos.response)
                    }
                    is Resource.Error -> {
                        Truth.assertThat(resource.data).isNotEqualTo(Data.Repos.response)
                    }
                }
            }
        }
}