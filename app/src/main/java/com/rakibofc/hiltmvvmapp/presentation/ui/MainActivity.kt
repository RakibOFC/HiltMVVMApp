package com.rakibofc.hiltmvvmapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rakibofc.hiltmvvmapp.R
import com.rakibofc.hiltmvvmapp.domain.model.User
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.textView)

        userViewModel.users.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading state
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                    Log.e("TAG", "Loading: ${resource.message}")
                }

                is Resource.Success -> {
                    // Show data
                    resource.data?.let { users ->
                        progressBar.visibility = View.GONE
                        textView.visibility = View.VISIBLE
                        setupUI(users)
                    }
                }

                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    textView.visibility = View.VISIBLE
                    Log.e("TAG", "Error message: ${resource.message}")
                }
            }
        }
    }

    private fun setupUI(users: List<User>) {

        var usersString = ""
        users.forEach {
            usersString += "${it.name} - ${it.email}\n"
        }

        textView.text = usersString
    }
}