package github.paulmburu.githublite.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.githublite.databinding.ListItemRepoBinding
import github.paulmburu.githublite.models.RepoPresentation


class RepoRecyclerAdapter :
    ListAdapter<RepoPresentation, RepoRecyclerAdapter.RepoViewHolder>(
        RepoComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }


    class RepoViewHolder(private val binding: ListItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            repo: RepoPresentation,
        ) {
            with(binding) {
                repoPresentation = repo
                executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): RepoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemRepoBinding.inflate(layoutInflater, parent, false)

                return RepoViewHolder(binding)
            }
        }
    }

    class RepoComparator : DiffUtil.ItemCallback<RepoPresentation>() {
        override fun areItemsTheSame(
            oldItem: RepoPresentation,
            newItem: RepoPresentation
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RepoPresentation,
            newItem: RepoPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}