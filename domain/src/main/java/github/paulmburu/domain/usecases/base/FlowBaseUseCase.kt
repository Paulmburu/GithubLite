package github.paulmburu.domain.usecases.base

interface FlowBaseUseCase<in Params, out T> {
    suspend operator fun invoke(params: Params): T
}