package com.myschool.shiftcft.adapter

import androidx.recyclerview.widget.RecyclerView
import com.myschool.shiftcft.databinding.UserItemBinding
import com.myschool.shiftcft.model.User

class UsersViewHolder(private val binding: UserItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {

        binding.tvDate.text = user.birthday
        binding.tvName.text = user.name
        binding.tvSurname.text = user.surname
        binding.tvPhone.text = user.phone
        binding.tvAddressCity.text = user.address
        binding.tvInitial.text = user.name.first().toString()

    }
}