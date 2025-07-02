package com.myschool.shiftcft.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.myschool.shiftcft.R
import com.myschool.shiftcft.adapter.UsersAdapter
import com.myschool.shiftcft.api.UsersApi
import com.myschool.shiftcft.database.AppDb
import com.myschool.shiftcft.databinding.FragmentUsersBinding
import com.myschool.shiftcft.itemdecoration.OffsetDecoration
import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.repository.ApiUsersRepository
import com.myschool.shiftcft.repository.RoomUsersRepository
import com.myschool.shiftcft.util.Callback
import com.myschool.shiftcft.viewmodel.UserViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class UsersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUsersBinding.inflate(inflater)
        val api = UsersApi.INSTANCE
        val networkRepository = ApiUsersRepository(api)

        val viewModel by activityViewModels<UserViewModel> {
            viewModelFactory {
                initializer {
                    UserViewModel(RoomUsersRepository(AppDb.getInstance(requireContext().applicationContext).userDao))
                }
            }
        }

        val adapter = UsersAdapter(
            object : UsersAdapter.UserListener {

                override fun onUserClicked(user: User) {

                    val profileFragment = ProfileFragment().apply {
                        arguments = Bundle().apply {
                            putString(ProfileFragment.ARG_USER_ID, user.id.value)

                        }
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, profileFragment)
                        .addToBackStack(null)
                        .commit()
                }

            }
        )

        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        binding.rcView.adapter = adapter
        binding.rcView.addItemDecoration(
            OffsetDecoration(resources.getDimensionPixelOffset(R.dimen.small_spacing))
        )

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.user)
            }.launchIn(lifecycleScope)


        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_add -> {
                    Log.d("NewUsers", "button clicked")
                    networkRepository.getUsers(10, object : Callback<List<User>> {
                        override fun onSuccess(data: List<User>) {
                            Log.d("NewUsers", "Users updated: $data")
                            viewModel.saveUsers(data)

                        }

                        override fun onError(throwable: Throwable) {
                            Log.e("UsersFragment", "Error fetching user", throwable)
                            showToast(context?.getString(R.string.error_message))
                        }
                    })
                    true
                }

                R.id.menu_info -> {
                    showToast(context?.getString(R.string.info_message))
                    true
                }

                R.id.menu_delete -> {
                    if (viewModel.isEmpty()) {
                        showToast(context?.getString(R.string.db_is_empty_message))
                    } else {
                        viewModel.deleteAllUsers()
                        showToast(context?.getString(R.string.db_deleted_message))
                    }

                    true
                }

                else -> false
            }
        }

        binding.buttonRefresh.setOnClickListener {
            if (viewModel.isEmpty()) {
                showToast(context?.getString(R.string.db_empty_message))
            } else {
                viewModel.deleteAllUsers()
                networkRepository.getUsers(10, object : Callback<List<User>> {
                    override fun onSuccess(data: List<User>) {
                        viewModel.saveUsers(data)
                        showToast(context?.getString(R.string.db_refreshed_message))
                    }

                    override fun onError(throwable: Throwable) {
                        Log.e("UsersFragment", "Error fetching user", throwable)
                        showToast(context?.getString(R.string.error_message))

                    }
                })
            }


        }

        return binding.root
    }

    private fun showToast(text: String?) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

}