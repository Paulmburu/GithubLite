package github.paulmburu.domain.usecases


import github.paulmburu.domain.models.User
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.base.FlowBaseUseCase

typealias InsertUserBaseUseCase = FlowBaseUseCase<User, Unit>

class InsertUserUseCase constructor(private val githubRepository: GithubRepository) :
    InsertUserBaseUseCase {
    override suspend fun invoke(params: User) {
        githubRepository.insertUser(params)
    }
}