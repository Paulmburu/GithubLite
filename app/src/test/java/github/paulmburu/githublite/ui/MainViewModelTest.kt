package github.paulmburu.githublite.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import github.paulmburu.domain.usecases.*
import github.paulmburu.githublite.ui.viewModels.MainViewModel
import github.paulmburu.githublite.util.ConnectivityProvider
import github.paulmburu.githublite.util.MainCoroutineRule
import github.paulmburu.githublite.util.SearchUserUiState
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    private lateinit var fetchUserUseCase: FetchUserUseCase

    @RelaxedMockK
    private lateinit var insertUserUseCase: InsertUserUseCase

    @RelaxedMockK
    private lateinit var getUserUseCase: GetUserUseCase

    @RelaxedMockK
    private lateinit var fetchFollowersUseCase: FetchFollowersUseCase

    @RelaxedMockK
    private lateinit var insertFollowersUseCase: InsertFollowersUseCase

    @RelaxedMockK
    private lateinit var getFollowersUseCase: GetFollowersUseCase

    @RelaxedMockK
    private lateinit var fetchFollowingUseCase: FetchFollowingUseCase

    @RelaxedMockK
    private lateinit var getFollowingUseCase: GetFollowingUseCase

    @RelaxedMockK
    private lateinit var insertFollowingUseCase: InsertFollowingUseCase

    @RelaxedMockK
    private lateinit var fetchReposUseCase: FetchReposUseCase

    @RelaxedMockK
    private lateinit var getReposUseCase: GetReposUseCase

    @RelaxedMockK
    private lateinit var insertReposUseCase: InsertReposUseCase


    @RelaxedMockK
    private lateinit var connectivityProvider: ConnectivityProvider

    private lateinit var viewModel : MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(
            fetchUserUseCase,
            insertUserUseCase,
            getUserUseCase,
            fetchFollowersUseCase,
            insertFollowersUseCase,
            getFollowersUseCase,
            fetchFollowingUseCase,
            getFollowingUseCase,
            insertFollowingUseCase,
            fetchReposUseCase,
            getReposUseCase,
            insertReposUseCase,
            connectivityProvider
        )
    }

    @Test
    fun `when clear search is called, search string should be set to emptyString and search result SearchUserUiState_Cleared ` (){
        viewModel.clearSearch()

        Truth.assertThat(viewModel.searchString.value).isEqualTo("")
        Truth.assertThat(viewModel.searchResult.value).isEqualTo(SearchUserUiState.Cleared)
    }


}