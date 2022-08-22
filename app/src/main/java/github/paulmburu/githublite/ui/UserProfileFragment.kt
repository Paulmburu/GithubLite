package github.paulmburu.githublite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import github.paulmburu.domain.models.Following
import github.paulmburu.githublite.R
import github.paulmburu.githublite.databinding.FragmentUserProfileBinding
import github.paulmburu.githublite.models.FollowerPresentation
import github.paulmburu.githublite.models.FollowingPresentation
import github.paulmburu.githublite.models.UserPresentation
import github.paulmburu.githublite.ui.adapters.FollowerRecyclerAdapter
import github.paulmburu.githublite.ui.adapters.FollowingRecyclerAdapter
import github.paulmburu.githublite.ui.viewModels.MainViewModel
import github.paulmburu.githublite.util.FetchFollowersUiState
import github.paulmburu.githublite.util.FetchFollowingUiState
import github.paulmburu.githublite.util.SearchUserUiState
import androidx.navigation.fragment.findNavController
import timber.log.Timber

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentUserProfileBinding? = null

    private val binding get() = _binding!!

    private lateinit var followerAdapter: FollowerRecyclerAdapter
    private lateinit var followingAdapter: FollowingRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDisplay()
        setObservers()
        binding.repositoriesView.setOnClickListener {
            findNavController().navigate(R.id.action_UserProfileFragment_to_RepositoriesFragment)
        }
    }

    private fun setDisplay() {
        followerAdapter = FollowerRecyclerAdapter()
        binding.followersRecyclerView.adapter = followerAdapter

        followingAdapter = FollowingRecyclerAdapter()
        binding.followingRecyclerView.adapter = followingAdapter
    }

    private fun setObservers() {
        viewModel.searchResult.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is SearchUserUiState.Loading -> {
                    displayUserProfileLoadingState()
                }

                is SearchUserUiState.Failure -> {
                    displayUserProfileFailedState()
                }

                is SearchUserUiState.Success -> {
                    displaySuccessState(user = uiState.user)
                }

                is SearchUserUiState.Empty -> {
                    displayUserProfileEmptyState()
                }

                is SearchUserUiState.Cleared -> {
                    displayUserProfileEmptyState()
                }
            }
        }

        viewModel.searchString.observe(viewLifecycleOwner) { query ->
            if (!query.isNullOrEmpty()) {
                viewModel.performSearch()
            }
        }
    }

    private fun displayUserProfileLoadingState() {
        with(binding) {
            loadingProgressBar.isVisible = true
            userProfileViews.isVisible = false
            followersViews.isVisible = false
            followingViews.isVisible = false
            searchViews.isVisible = false
            errorView.isVisible = false
        }
    }

    private fun displaySuccessState(user: UserPresentation) {
        with(binding) {
            user.avatarUrl.let {
                Glide.with(this@UserProfileFragment)
                    .load(it)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(userAvatarImageView)
            }

            nameTextView.text = user.name
            usernameTextView.text = user.login
            bioTextView.text = user.bio
            reposCountTextView.text = user.repos.toString()
            followersCountTextView.text = user.followers.toString()
            followingCountTextView.text = user.following.toString()
            userProfileViews.isVisible = true
            loadingProgressBar.isVisible = false
            searchViews.isVisible = false
        }

        viewModel.followersResult.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is FetchFollowersUiState.Loading -> {
//                    displayLoadingState()
                }

                is FetchFollowersUiState.Failure -> {
//                    displayFailedState(message = uiState.message)
                    Timber.d("Failure: ${uiState.message}")
                }

                is FetchFollowersUiState.Success -> {
                    displayFollowersSuccessState(uiState.followers)
                    Timber.d("Success: ${uiState.followers}")
                }

                is FetchFollowersUiState.Empty -> {
//                    displayEmptyState()
                    Timber.d("Empty data")
                }
            }
        }

        viewModel.followingResult.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is FetchFollowingUiState.Loading -> {
//                    displayLoadingState()
                }

                is FetchFollowingUiState.Failure -> {
//                    displayFailedState(message = uiState.message)
                    Timber.d("Failure: ${uiState.message}")
                }

                is FetchFollowingUiState.Success -> {
                    displayFollowingSuccessState(uiState.following)
                }

                is FetchFollowingUiState.Empty -> {
//                    displayEmptyState()
                    Timber.d("Empty data")
                }
            }
        }
    }

    private fun displayUserProfileEmptyState(){
        with(binding) {
            searchViews.isVisible = true
            loadingProgressBar.isVisible = true
            userProfileViews.isVisible = false
            followersViews.isVisible = false
            followingViews.isVisible = false
            errorView.isVisible = false
        }
    }

    private fun displayUserProfileFailedState(){
        with(binding) {
            errorView.isVisible = true
            searchViews.isVisible = false
            loadingProgressBar.isVisible = false
            userProfileViews.isVisible = false
            followersViews.isVisible = false
            followingViews.isVisible = false
        }
    }

    private fun displayFollowersSuccessState(followers: List<FollowerPresentation>) {
        followerAdapter.submitList(followers)
        with(binding){
            followersViews.isVisible = true
        }
    }
    private fun displayFollowingSuccessState(following: List<FollowingPresentation>) {
        followingAdapter.submitList(following)
        with(binding){
            followingViews.isVisible = true
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}