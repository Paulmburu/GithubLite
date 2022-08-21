package github.paulmburu.domain.usecases

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.base.FlowBaseUseCase

typealias InsertFollowingBaseUseCase = FlowBaseUseCase<List<Following>, Unit>

class InsertFollowingUseCase constructor(private val githubRepository: GithubRepository) :
    InsertFollowingBaseUseCase {
    override suspend fun invoke(params: List<Following>) {
        githubRepository.insertFollowing(params)
    }
}