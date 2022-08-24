package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetReposUseCaseTest {
    private lateinit var getReposUseCaseTest: GetReposBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        getReposUseCaseTest = GetReposUseCase(
            githubRepository
        )
    }

    @Test
    fun `When FetchFollowersUseCase is called, Followers should be returned`() =
        runBlocking {
            getReposUseCaseTest("Paulmburu").collect { resource ->
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