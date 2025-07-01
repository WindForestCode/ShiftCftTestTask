package com.myschool.shiftcft.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
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


        binding.tvNameSurname.text = buildString {
            append(user?.name?.first)
            append(" ")
            append(user?.name?.last)
        }
        binding.tvSex.text = user?.gender
        binding.tvEmail.text = user?.email
        binding.tvPhone.text = user?.phone
        Glide.with(binding.imageAvatar)
            .load(user?.picture?.medium)
            .transform(CircleCrop())
            .into(binding.imageAvatar)
        binding.tvCellphone.text = user?.cell
        binding.tvDateBirth.text = user?.dob?.date?.take(10)
        binding.tvUsername.text = user?.login?.username
        binding.tvAge.text = user?.dob?.age.toString()
        binding.tvAddress.text = buildString {
            append(user?.location?.country)
            append(", ")
            append(user?.location?.city)
            append(", ")
            append(user?.location?.street?.name)
            append("-")
            append(user?.location?.street?.number)
        }

        binding.tvEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${user?.email}")
            }
            startActivity(Intent.createChooser(emailIntent, "Отправить email через:"))
        }

        binding.tvPhone.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${user?.phone}")
            }
            startActivity(phoneIntent)
        }

        binding.tvCellphone.setOnClickListener {
            val cellIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${user?.cell}")
            }
            startActivity(cellIntent)
        }

        binding.tvAddress.setOnClickListener {
            val addressUri = Uri.parse("geo:0,0?q=${Uri.encode(binding.tvAddress.text.toString())}")
            val mapIntent = Intent(Intent.ACTION_VIEW, addressUri).apply {
                setPackage("com.google.android.apps.maps")
            }
            startActivity(mapIntent)
        }
        return binding.root
    }


}