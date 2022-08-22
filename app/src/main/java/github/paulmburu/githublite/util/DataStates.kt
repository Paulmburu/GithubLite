package github.paulmburu.githublite.util

import github.paulmburu.githublite.models.FollowerPresentation
import github.paulmburu.githublite.models.FollowingPresentation
import github.paulmburu.githublite.models.RepoPresentation
import github.paulmburu.githublite.models.UserPresentation


sealed class SearchUserUiState {

    object Loading : SearchUserUiState()

    object Empty : SearchUserUiState()

    object Cleared : SearchUserUiState()

    data class Failure(val message: String) : SearchUserUiState()

    data class Success(val user: UserPresentation) : SearchUserUiState()

}

sealed class FetchFollowersUiState {

    object Loading : FetchFollowersUiState()

    object Empty : FetchFollowersUiState()

    data class Failure(val message: String) : FetchFollowersUiState()

    data class Success(val followers: List<FollowerPresentation>) : FetchFollowersUiState()

}

sealed class FetchFollowingUiState {

    object Loading : FetchFollowingUiState()

    object Empty : FetchFollowingUiState()

    data class Failure(val message: String) : FetchFollowingUiState()

    data class Success(val following: List<FollowingPresentation>) : FetchFollowingUiState()
}

sealed class FetchReposUiState {

    object Loading : FetchReposUiState()

    object Empty : FetchReposUiState()

    data class Failure(val message: String) : FetchReposUiState()

    data class Success(val repos: List<RepoPresentation>) : FetchReposUiState()
}