package com.nikfen.network_db_task_02.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nikfen.network_db_task_02.databinding.UserFullViewFragmentBinding
import com.nikfen.network_db_task_02.viewmodel.UserFullViewViewModel
import com.nikfen.network_db_task_02.viewmodel.factory.UserFullViewViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFullViewFragment : Fragment() {

    private lateinit var binding: UserFullViewFragmentBinding

    @Inject
    lateinit var factory: UserFullViewViewModel.Factory

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
        val viewModel: UserFullViewViewModel by viewModels {
            UserFullViewViewModelFactory(factory, args.id)
        }

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