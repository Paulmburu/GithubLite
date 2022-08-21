package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.GithubRepositoryFake
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFollowingUseCaseTest {
    private lateinit var getFollowingUseCaseTest: GetFollowingBaseUseCase

    @Before
    fun setUp() {
        val githubRepository = GithubRepositoryFake()
        getFollowingUseCaseTest = GetFollowingUseCase(
            githubRepository
        )
    }

    @Test
    fun `When GetFollowingUseCase is called, Followers should be returned`() =
        runBlocking {
            getFollowingUseCaseTest("Paulmburu").collect { resource ->
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