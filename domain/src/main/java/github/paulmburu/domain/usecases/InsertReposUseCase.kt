package github.paulmburu.domain.usecases

import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.repository.GithubRepository
import github.paulmburu.domain.usecases.base.FlowBaseUseCase

typealias InsertReposBaseUseCase = FlowBaseUseCase<List<Repo>, Unit>

class InsertReposUseCase constructor(private val githubRepository: GithubRepository) :
    InsertReposBaseUseCase{
    override suspend fun invoke(params: List<Repo>) {
        githubRepository.insertRepos(params)
    }
}