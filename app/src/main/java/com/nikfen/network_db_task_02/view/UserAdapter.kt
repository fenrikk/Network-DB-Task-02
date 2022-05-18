package com.nikfen.network_db_task_02.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikfen.network_db_task_02.databinding.UserItemBinding
import com.nikfen.network_db_task_02.model.remote.response.UserData

class UserAdapter(
    private val context: Context
) : ListAdapter<UserData, UserAdapter.UserViewHolder>(UserDiffCallBack()) {

    class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Glide.with(context).load(getItem(position).picture.medium)
            .into(holder.binding.itemUserImage)
        val fullName = getItem(position).name.first + " " + getItem(position).name.last
        holder.binding.itemUserName.text = fullName
    }
}

class UserDiffCallBack : DiffUtil.ItemCallback<UserData>() {
    override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return newItem == oldItem
    }
}