package com.nikfen.network_db_task_02.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.bumptech.glide.Glide
import com.nikfen.network_db_task_02.databinding.UserFullViewFragmentBinding
import com.nikfen.network_db_task_02.model.local.database.UserDatabase
import com.nikfen.network_db_task_02.viewmodel.UserFullViewViewModel
import com.nikfen.network_db_task_02.viewmodel.factory.UserFullViewViewModelFactory

class UserFullViewFragment : Fragment() {

    private lateinit var binding: UserFullViewFragmentBinding
    private val viewModel: UserFullViewViewModel by viewModels {
        val db = Room.databaseBuilder(
            requireContext(),
            UserDatabase::class.java, "database-user"
        ).build()
        val userDao = db.userDao()
        UserFullViewViewModelFactory(userDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserFullViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: UserFullViewFragmentArgs by navArgs()

        viewModel.fetchUser(args.id)

        viewModel.getUser().observe(viewLifecycleOwner) {
            Glide.with(requireContext()).load(it.picture).into(binding.userImage)
            binding.firstName.text = it.firstName
            binding.lastName.text = it.lastName
            binding.old.text = it.old.toString()
            binding.email.text = it.email
            binding.phone.text = it.phone
            binding.gender.text = it.gender
        }
    }
}