package github.paulmburu.githublite.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.paulmburu.common.Resource
import github.paulmburu.domain.usecases.*
import github.paulmburu.githublite.mappers.toPresentation
import github.paulmburu.githublite.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val fetchFollowersUseCase: FetchFollowersUseCase,
    private val insertFollowersUseCase: InsertFollowersUseCase,
    private val getFollowersUseCase: GetFollowersUseCase,
    private val fetchFollowingUseCase: FetchFollowingUseCase,
    private val getFollowingUseCase: GetFollowingUseCase,
    private val insertFollowingUseCase: InsertFollowingUseCase,
    private val fetchReposUseCase: FetchReposUseCase,
    private val getReposUseCase: GetReposUseCase,
    private val insertReposUseCase: InsertReposUseCase,
    private val connectivityProvider: ConnectivityProvider,
) :
    ViewModel() {

    private val mutableSearchString = MutableLiveData<String>()
    val searchString: LiveData<String>
        get() = mutableSearchString

    private val mutableSearchResult = MutableLiveData<SearchUserUiState>()
    val searchResult: LiveData<SearchUserUiState>
        get() = mutableSearchResult

    private val mutableReposResult = MutableLiveData<FetchReposUiState>()
    val reposResult: LiveData<FetchReposUiState>
        get() = mutableReposResult

    private val mutableFollowersResult = MutableLiveData<FetchFollowersUiState>()
    val followersResult: LiveData<FetchFollowersUiState>
        get() = mutableFollowersResult

    private val mutableFollowingResult = MutableLiveData<FetchFollowingUiState>()
    val followingResult: LiveData<FetchFollowingUiState>
        get() = mutableFollowingResult

    fun setSearchQuery(query: String?) {
        if (query.isNullOrEmpty()) {
            clearSearch()
        } else {
            mutableSearchString.value = query.toString().trim()
            viewModelScope.launch { delay(400) }
        }
    }

    fun performSearch() = viewModelScope.launch {
        if (searchString.value.isNullOrEmpty()) return@launch

        val query = searchString.value.toString()
        if (connectivityProvider.isNetworkAvailable()) {
            searchFromInternet(query)
            fetchFollowers(query)
            fetchFollowing(query)
            fetchRepos(query)
        } else {
            searchFromDatabase(query)
            getFollowers(query)
            getFollowing(query)
            getRepos(query)
        }

    }

    private suspend fun searchFromInternet(userQuery: String) {
        fetchUserUseCase(userQuery)
            .onStart {
                mutableSearchResult.value = SearchUserUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableSearchResult.value = SearchUserUiState.Empty
                        } else {
                            mutableSearchResult.value = SearchUserUiState.Success(
                                user = resource.data!!.toPresentation()
                            )
                            insertUserUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                }

            }
    }

    private suspend fun searchFromDatabase(userQuery: String) {
        getUserUseCase(userQuery)
            .onStart {
                mutableSearchResult.value = SearchUserUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableSearchResult.value = SearchUserUiState.Empty
                        } else {
                            mutableSearchResult.value = SearchUserUiState.Success(
                                user = resource.data!!.toPresentation()
                            )
                            insertUserUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }

                    else -> {

                    }
                }

            }
    }

    private suspend fun fetchFollowers(userQuery: String) {
        fetchFollowersUseCase(userQuery)
            .onStart {
                mutableFollowersResult.value = FetchFollowersUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableFollowersResult.value = FetchFollowersUiState.Empty
                        } else {
                            mutableFollowersResult.value = FetchFollowersUiState.Success(
                                followers = resource.data!!.map { it.toPresentation() }
                            )
                            insertFollowersUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                }

            }
    }

    private suspend fun getFollowers(userQuery: String) {
        getFollowersUseCase(userQuery)
            .onStart {
                mutableFollowersResult.value = FetchFollowersUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableFollowersResult.value = FetchFollowersUiState.Empty
                        } else {
                            mutableFollowersResult.value = FetchFollowersUiState.Success(
                                followers = resource.data!!.map { it.toPresentation() }
                            )
                            insertFollowersUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                    else -> {

                    }
                }
            }
    }

    private suspend fun fetchFollowing(userQuery: String) {
        fetchFollowingUseCase(userQuery)
            .onStart {
                mutableFollowingResult.value = FetchFollowingUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableFollowingResult.value = FetchFollowingUiState.Empty
                        } else {
                            mutableFollowingResult.value = FetchFollowingUiState.Success(
                                following = resource.data!!.map { it.toPresentation() }
                            )
                            insertFollowingUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                }

            }
    }

    private suspend fun getFollowing(userQuery: String) {
        getFollowingUseCase(userQuery)
            .onStart {
                mutableFollowingResult.value = FetchFollowingUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableFollowingResult.value = FetchFollowingUiState.Empty
                        } else {
                            mutableFollowingResult.value = FetchFollowingUiState.Success(
                                following = resource.data!!.map { it.toPresentation() }
                            )
                            insertFollowingUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                    else -> {

                    }
                }
            }
    }

    private suspend fun fetchRepos(userQuery: String) {
        fetchReposUseCase(userQuery)
            .onStart {
                mutableReposResult.value = FetchReposUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableReposResult.value = FetchReposUiState.Empty
                        } else {
                            mutableReposResult.value = FetchReposUiState.Success(
                                repos = resource.data!!.map { it.toPresentation() }
                            )
                            insertReposUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                }

            }
    }

    private suspend fun getRepos(userQuery: String) {
        getReposUseCase(userQuery)
            .onStart {
                mutableReposResult.value = FetchReposUiState.Loading
            }
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {

                        if (resource.data == null) {
                            mutableReposResult.value = FetchReposUiState.Empty
                        } else {
                            mutableReposResult.value = FetchReposUiState.Success(
                                repos = resource.data!!.map { it.toPresentation() }
                            )
                            insertReposUseCase(resource.data!!)
                        }

                    }
                    is Resource.Error -> {
                        mutableSearchResult.value = SearchUserUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                    else -> {

                    }
                }
            }
    }

    fun clearSearch() {
        mutableSearchString.value = ""
        mutableSearchResult.value = SearchUserUiState.Cleared
    }


}