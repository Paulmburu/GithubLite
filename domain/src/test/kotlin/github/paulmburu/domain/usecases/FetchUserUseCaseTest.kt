package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect
import org.junit.Before
import org.junit.Test

class FetchUserUseCaseTest {

    private lateinit var fetchUserUseCaseTest: FetchUserBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        fetchUserUseCaseTest = FetchUserUseCase(
            githubRepository
        )
    }

    @Test
    fun `When FetchUserUseCase is called, User should be returned`() =
        runBlocking {
            fetchUserUseCaseTest("Paulmburu").collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        Truth.assertThat(resource.data).isEqualTo(Data.user)
                    }
                    is Resource.Error -> {
                        Truth.assertThat(resource.data).isNotEqualTo(Data.user)
                    }
                }
            }
        }
}