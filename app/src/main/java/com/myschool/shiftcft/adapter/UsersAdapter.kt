package com.myschool.shiftcft.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.myschool.shiftcft.databinding.UserItemBinding
import com.myschool.shiftcft.model.User

class UsersAdapter(private val listener: UserListener) :
    ListAdapter<User, UsersViewHolder>(UserDiffCallback()) {

    interface UserListener {
        fun onUserClicked(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = UsersViewHolder(binding)

        binding.root.setOnClickListener {
            val user = getItem(viewHolder.adapterPosition)
            listener.onUserClicked(user)

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    fun getCurrentListItems(): List<User> {
        return currentList
    }
}