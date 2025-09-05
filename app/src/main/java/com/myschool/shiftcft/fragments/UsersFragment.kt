package com.myschool.shiftcft.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.myschool.shiftcft.R
import com.myschool.shiftcft.adapter.UsersAdapter
import com.myschool.shiftcft.databinding.FragmentUsersBinding
import com.myschool.shiftcft.itemdecoration.OffsetDecoration
import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class UsersFragment : Fragment(), CountDialogFragment.CountInputListener {

    private var count: Int = 0
    private val viewModel: UserViewModel by viewModels()
    private var isFirstLoad = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUsersBinding.inflate(inflater)

        val adapter = UsersAdapter(
            object : UsersAdapter.UserListener {

                override fun onUserClicked(user: User) {

                    val profileFragment = ProfileFragment().apply {
                        arguments = Bundle().apply {
                            putString(ProfileFragment.ARG_USER_ID, user.id.value)

                        }
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, profileFragment, "profileFragment")
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
                val previousSize = adapter.itemCount
                adapter.submitList(it.user) {
                    if (!isFirstLoad && it.user.size > previousSize) {
                        binding.rcView.smoothScrollToPosition(0)
                    }
                    isFirstLoad = false
                }
            }.launchIn(lifecycleScope)


        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_add -> {
                    Log.d("NewUsers", "button clicked")
                    showCountDialog()


                    true
                }

                R.id.menu_info -> {
                    showToast(context?.getString(R.string.info_message))
                    true
                }

                R.id.menu_delete -> {
                    viewModel.isEmpty { isEmpty ->
                        if (isEmpty) {
                            showToast(context?.getString(R.string.db_is_empty_message))
                        } else {
                            viewModel.deleteAllUsers()
                            showToast(context?.getString(R.string.db_deleted_message))
                        }
                    }

                    true
                }

                else -> false
            }
        }

        binding.buttonRefresh.setOnClickListener {
            viewModel.isEmpty { isEmpty ->
                if (isEmpty) {
                    showToast(context?.getString(R.string.db_empty_message))
                } else {

                    viewModel.deleteAllUsers()
                    viewModel.getUsers(10)
                }
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

    private fun showCountDialog() {
        val dialog = CountDialogFragment()
        dialog.listener = this
        dialog.show(parentFragmentManager, "CountDialog")
    }


    override fun onCountInput(count: Int) {
        this.count = count
        viewModel.getUsers(count)
    }

}