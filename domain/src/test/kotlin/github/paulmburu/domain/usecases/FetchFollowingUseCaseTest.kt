package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchFollowingUseCaseTest {
    private lateinit var fetchfollowingUseCaseTest: FetchFollowingBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        fetchfollowingUseCaseTest = FetchFollowingUseCase(
            githubRepository
        )
    }

    @Test
    fun `When FetchFollowingUseCase is called, Following should be returned`() =
        runBlocking {
            fetchfollowingUseCaseTest("Paulmburu").collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        Truth.assertThat(resource.data).isEqualTo(Data.FollowingData.response)
                    }
                    is Resource.Error -> {
                        Truth.assertThat(resource.data).isNotEqualTo(Data.FollowingData.response)
                    }
                }
            }
        }
}