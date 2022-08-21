package github.paulmburu.domain.usecases

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.User
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.base.FlowBaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

typealias GetUserBaseUseCase = FlowBaseUseCase<String, Flow<Resource<User>>>
class GetUserUseCase constructor(private val githubRepository: GithubRepository) :
    GetUserBaseUseCase{
    override suspend fun invoke(params: String): Flow<Resource<User>> = flow {
        val result = githubRepository.getUser(params)
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