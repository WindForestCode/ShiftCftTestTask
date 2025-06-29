package com.myschool.shiftcft.adapter

import androidx.recyclerview.widget.RecyclerView
import com.myschool.shiftcft.databinding.UserItemBinding
import com.myschool.shiftcft.model.User

class UsersViewHolder(private val binding: UserItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {

        binding.tvDate.text = user.dob.date
        binding.tvName.text = user.name.first
        binding.tvSurname.text = user.name.last
        binding.tvPhone.text = user.phone
        binding.tvAddressCity.text = user.location.city
        binding.tvInitial.text = user.name.first.first().toString()

    }
}