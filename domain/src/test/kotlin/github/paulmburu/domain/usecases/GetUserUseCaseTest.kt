package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserUseCaseTest {
    private lateinit var getUserUseCase: GetUserBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        getUserUseCase = GetUserUseCase(
            githubRepository
        )
    }

    @Test
    fun `When GetUserUseCase is called, User should be returned`() =
        runBlocking {
            getUserUseCase("Paulmburu").collect { resource ->
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