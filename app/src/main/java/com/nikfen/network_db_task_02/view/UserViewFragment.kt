package com.nikfen.network_db_task_02.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.nikfen.network_db_task_02.databinding.UserViewFragmentBinding
import com.nikfen.network_db_task_02.model.local.database.UserDatabase
import com.nikfen.network_db_task_02.model.remote.RemoteInstance
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel
import com.nikfen.network_db_task_02.viewmodel.factory.UserViewViewModelFactory

class UserViewFragment : Fragment() {

    private lateinit var binding: UserViewFragmentBinding
    private val viewModel: UserViewViewModel by viewModels {
        val db = Room.databaseBuilder(
            requireContext(),
            UserDatabase::class.java, "database-user"
        ).build()
        val userDao = db.userDao()
        val userApi = RemoteInstance.getApi()
        UserViewViewModelFactory(userApi, userDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter = UserAdapter(requireContext(), onItemClicked = {
            val action = UserViewFragmentDirections.actionUserViewFragmentToUserFullViewFragment(it)
            findNavController().navigate(action)
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