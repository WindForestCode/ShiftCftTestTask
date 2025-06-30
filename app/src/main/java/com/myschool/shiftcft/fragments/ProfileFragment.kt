package com.myschool.shiftcft.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.myschool.shiftcft.R
import com.myschool.shiftcft.database.AppDb
import com.myschool.shiftcft.databinding.FragmentProfileBinding
import com.myschool.shiftcft.repository.RoomUsersRepository


class ProfileFragment : Fragment() {
    companion object {
        const val ARG_USER_ID = "ARG_USER_ID"

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater)

        val id = arguments?.getString(ARG_USER_ID) ?: "0"
        val repository =
            RoomUsersRepository(AppDb.getInstance(requireContext().applicationContext).userDao)
        val user = repository.getUser(id.toLong())
        binding.tvName.text = user?.name?.first
        binding.tvSex.text = user?.gender
        binding.tvEmail.text = user?.email
        binding.tvPhone.text = user?.phone
        Glide.with(binding.imageAvatar)
            .load(user?.picture?.medium)
            .transform(CircleCrop())
            .into(binding.imageAvatar)
        binding.tvCellphone.text = user?.cell
        binding.tvDateBirth.text = user?.dob?.date
        binding.tvUsername.text = user?.login?.username

        return binding.root
    }


}