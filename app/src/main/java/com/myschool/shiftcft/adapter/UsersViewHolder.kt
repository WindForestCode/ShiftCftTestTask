package com.myschool.shiftcft.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.myschool.shiftcft.databinding.UserItemBinding
import com.myschool.shiftcft.model.User

class UsersViewHolder(private val binding: UserItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {

        binding.tvDate.text = user.dob.date.take(10)
        binding.tvNameSurname.text = user.name.first
        binding.tvSurname.text = user.name.last
        binding.tvPhone.text = user.phone
        binding.tvAddressCity.text = user.location.city
        Glide.with(binding.imageAvatar)
            .load(user.picture.thumbnail)
            .transform(CircleCrop())
            .into(binding.imageAvatar)

    }
}