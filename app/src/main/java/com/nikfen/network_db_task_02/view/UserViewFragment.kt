package com.nikfen.network_db_task_02.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikfen.network_db_task_02.databinding.UserViewFragmentBinding
import com.nikfen.network_db_task_02.model.remote.UserApplication
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel

class UserViewFragment : Fragment() {

    private lateinit var binding: UserViewFragmentBinding
    private val viewModel: UserViewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter = UserAdapter(requireContext())

        viewModel.fetchUserList((activity?.application as? UserApplication)?.getApi())

        viewModel.getUserList().observe(viewLifecycleOwner) {
            Log.d("UserApp", "onViewCreated: ${it[0].gender}")
            userAdapter.submitList(it)
            binding.userListRecycleView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = userAdapter
            }
        }
    }
}