package com.picpay.desafio.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.model.User

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    var users = emptyList<User>()

    fun updateList(newUserList: List<User>) {
        val result = DiffUtil.calculateDiff(
            UserListDiffCallback(
                users,
                newUserList
            )
        )
        result.dispatchUpdatesTo(this)
        users = newUserList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val binding: ListItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_user,
            parent,
            false
        )

        return UserListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}