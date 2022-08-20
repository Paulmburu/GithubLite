package github.paulmburu.domain.usecases

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.base.FlowBaseUseCase

typealias InsertFollowingBaseUseCase = FlowBaseUseCase<List<Follower>, Unit>

class InsertFollowingUseCase constructor(private val githubRepository: GithubRepository) :
    InsertFollowingBaseUseCase {
    override suspend fun invoke(params: List<Follower>) {
        githubRepository.insertFollowing(params)
    }
}