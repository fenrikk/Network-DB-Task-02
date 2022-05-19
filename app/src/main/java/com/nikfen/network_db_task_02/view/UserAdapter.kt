package com.nikfen.network_db_task_02.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.nikfen.network_db_task_02.databinding.UserItemBinding
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.response.UserData

class UserAdapter(
    private val context: Context,
    private val userInterface: UserInterface
) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallBack()) {

    class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Glide.with(context).load(getItem(position).picture)
            .into(holder.binding.itemUserImage)
        val fullName = getItem(position).firstName + " " + getItem(position).lastName
        holder.binding.itemUserName.text = fullName
        holder.binding.root.setOnClickListener {
            userInterface.onclick(getItem(position))
        }
    }

    interface UserInterface {
        fun onclick(userData: User)
    }
}

class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}