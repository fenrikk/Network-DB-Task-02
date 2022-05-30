package com.nikfen.network_db_task_02.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikfen.network_db_task_02.databinding.UserViewFragmentBinding
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserViewFragment : Fragment() {

    private lateinit var binding: UserViewFragmentBinding
    private val viewModel by viewModel<UserViewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter = UserAdapter(onItemClicked = {
            val action = UserViewFragmentDirections.actionUserViewFragmentToUserFullViewFragment(it)
            findNavController().navigate(action)
        }, onEndReached = {
            viewModel.loadUsers()
        })

        binding.userListRecycleView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }

        viewModel.getUserList().observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
    }
}