package com.nikfen.network_db_task_02.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikfen.network_db_task_02.R
import com.nikfen.network_db_task_02.databinding.UserItemBinding
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.other.RENDER_DISTANCE

class UserAdapter(
    private val context: Context,
    private val onItemClicked: (String) -> Unit,
    private val onEndReached: () -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
        if (itemCount - position == RENDER_DISTANCE) {
            onEndReached()
        }
    }

    class UserViewHolder(private val binding: UserItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, onItemClicked: (String) -> Unit) {
            Glide.with(binding.itemUserImage).load(user.picture)
                .circleCrop()
                .into(binding.itemUserImage)
            binding.itemUserName.text =
                context.getString(R.string.name, user.firstName, user.lastName)
            binding.root.setOnClickListener {
                onItemClicked(user.uid)
            }
        }
    }
}

class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}