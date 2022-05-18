package com.nikfen.network_db_task_02.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikfen.network_db_task_02.databinding.UserFullViewFragmentBinding

class UserFullViewFragment : Fragment() {

    private lateinit var binding: UserFullViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserFullViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}