package com.nikfen.network_db_task_02.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikfen.network_db_task_02.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}