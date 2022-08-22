package github.paulmburu.githublite.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.githublite.databinding.ListItemFollowerBinding
import github.paulmburu.githublite.models.FollowerPresentation


class FollowerRecyclerAdapter :
    ListAdapter<FollowerPresentation, FollowerRecyclerAdapter.FollowerViewHolder>(
        FollowerComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val follower = getItem(position)
        holder.bind(follower)
    }


    class FollowerViewHolder(private val binding: ListItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            follower: FollowerPresentation,
        ) {
            with(binding) {
                followerPresentation = follower
                executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): FollowerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFollowerBinding.inflate(layoutInflater, parent, false)

                return FollowerViewHolder(binding)
            }
        }

    }

    class FollowerComparator : DiffUtil.ItemCallback<FollowerPresentation>() {
        override fun areItemsTheSame(
            oldItem: FollowerPresentation,
            newItem: FollowerPresentation
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FollowerPresentation,
            newItem: FollowerPresentation
        ): Boolean {
            return oldItem.id == newItem.id

        }

    }
}