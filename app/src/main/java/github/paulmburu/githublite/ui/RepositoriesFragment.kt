package github.paulmburu.githublite.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import github.paulmburu.githublite.databinding.FragmentRepositoriesBinding
import github.paulmburu.githublite.models.RepoPresentation
import github.paulmburu.githublite.ui.adapters.RepoRecyclerAdapter
import github.paulmburu.githublite.ui.viewModels.MainViewModel
import github.paulmburu.githublite.util.FetchReposUiState

class RepositoriesFragment : Fragment() {

    private var _binding: FragmentRepositoriesBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var repoAdapter: RepoRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDisplay()
        setObservers()
    }

    private fun setDisplay() {
        repoAdapter = RepoRecyclerAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.reposRecyclerView.apply {
            adapter = repoAdapter
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }

    }

    private fun setObservers() {
        viewModel.reposResult.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is FetchReposUiState.Loading -> {
                    displayReposLoadingState()
                }

                is FetchReposUiState.Failure -> {
                    displayFailedState()
                }

                is FetchReposUiState.Success -> {
                    displayReposSuccessState(repos = uiState.repos)
                }

                is FetchReposUiState.Empty -> {
//                    displayEmptyState()
                }


            }
        }
    }

    private fun displayReposLoadingState() {
        with(binding) {
            loadingReposProgressBar.isVisible = true
            reposRecyclerView.isVisible = false
            errorView.isVisible = false
        }
    }

    private fun displayReposSuccessState(repos: List<RepoPresentation>) {
        repoAdapter.submitList(repos)
        with(binding) {
            loadingReposProgressBar.isVisible = false
            errorView.isVisible = false
            reposRecyclerView.isVisible = true
        }
    }
    private fun displayFailedState(){
        with(binding) {
            errorView.isVisible = true
            loadingReposProgressBar.isVisible = false
            reposRecyclerView.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}