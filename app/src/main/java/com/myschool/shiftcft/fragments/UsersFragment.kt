package com.myschool.shiftcft.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.myschool.shiftcft.R
import com.myschool.shiftcft.api.UsersApi
import com.myschool.shiftcft.databinding.FragmentUsersBinding
import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.repository.ApiUsersRepository
import com.myschool.shiftcft.util.Callback


class UsersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUsersBinding.inflate(inflater)
        val api = UsersApi.INSTANCE
        val networkRepository = ApiUsersRepository(api)

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_add -> {
                    Log.d("NewUsers", "button clicked")
                    networkRepository.getUsers(10, object : Callback<List<User>> {
                        override fun onSuccess(data: List<User>) {
                            Log.d("NewUsers", "Users updated: $data")

                        }

                        override fun onError(throwable: Throwable) {
                            Log.e("UsersFragment", "Error fetching user", throwable)
                        }
                    })
                    true
                }

                R.id.menu_info -> {
                    Toast.makeText(context, "Возьмите в ШИФТ ЦФТ)", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        return binding.root
    }

}