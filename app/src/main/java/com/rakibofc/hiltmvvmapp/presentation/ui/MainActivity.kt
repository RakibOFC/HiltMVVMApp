package com.rakibofc.hiltmvvmapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.rakibofc.hiltmvvmapp.R
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        userViewModel.users.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading state
                    Log.e("TAG", "Loading: ${resource.message}")
                }

                is Resource.Success -> {
                    // Show data
                    resource.data?.let { users ->
                        // Handle user list
                        Log.e("TAG", "onCreate - users: ${Gson().toJson(users)}")
                    }
                }

                is Resource.Error -> {
                    // Show error message
                    Log.e("TAG", "Error message: ${resource.message}")
                }
            }
        }
    }
}