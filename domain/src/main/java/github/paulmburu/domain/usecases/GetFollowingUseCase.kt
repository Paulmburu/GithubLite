package github.paulmburu.domain.usecases

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.base.FlowBaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

typealias GetFollowingBaseUseCase = FlowBaseUseCase<String, Flow<Resource<List<Follower>>>>

class GetFollowingUseCase constructor(private val githubRepository: GithubRepository) :
    GetFollowersBaseUseCase {
    override suspend fun invoke(params: String): Flow<Resource<List<Follower>>> = flow {
        val result = githubRepository.getFollowing()
        result.collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val data = resource.data!!
                    emit(
                        Resource.Success(data)
                    )
                }
                is Resource.Error -> {
                    emit(Resource.Error(message = resource.message))
                }
            }
        }
    }
}