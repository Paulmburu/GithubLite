package github.paulmburu.githublite.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.githublite.databinding.ListItemFollowingBinding
import github.paulmburu.githublite.models.FollowingPresentation


class FollowingRecyclerAdapter :
    ListAdapter<FollowingPresentation, FollowingRecyclerAdapter.FollowingViewHolder>(
        FollowingComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        return FollowingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val following = getItem(position)
        holder.bind(following)
    }


    class FollowingViewHolder(private val binding: ListItemFollowingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            Following: FollowingPresentation,
        ) {
            with(binding) {
                followingPresentation = Following
                executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): FollowingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFollowingBinding.inflate(layoutInflater, parent, false)

                return FollowingViewHolder(binding)
            }
        }

    }

    class FollowingComparator : DiffUtil.ItemCallback<FollowingPresentation>() {
        override fun areItemsTheSame(
            oldItem: FollowingPresentation,
            newItem: FollowingPresentation
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FollowingPresentation,
            newItem: FollowingPresentation
        ): Boolean {
            return oldItem.id == newItem.id

        }

    }
}