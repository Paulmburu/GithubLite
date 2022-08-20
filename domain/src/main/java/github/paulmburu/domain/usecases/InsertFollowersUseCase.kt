package github.paulmburu.domain.usecases

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.base.FlowBaseUseCase

typealias InsertFollowersBaseUseCase = FlowBaseUseCase<List<Follower>, Unit>

class InsertFollowersUseCase constructor(private val githubRepository: GithubRepository) :
    InsertFollowersBaseUseCase {
    override suspend fun invoke(params: List<Follower>) {
        githubRepository.insertFollowers(params)
    }
}