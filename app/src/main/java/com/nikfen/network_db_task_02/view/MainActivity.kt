package com.nikfen.network_db_task_02.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.nikfen.network_db_task_02.R
import com.nikfen.network_db_task_02.model.local.LocalInstance
import com.nikfen.network_db_task_02.model.local.database.UserDatabase
import com.nikfen.network_db_task_02.other.LOCAL_DATABASE_NAME

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this,
            UserDatabase::class.java, LOCAL_DATABASE_NAME
        ).build()
        LocalInstance.setDatabase(db)
    }
}