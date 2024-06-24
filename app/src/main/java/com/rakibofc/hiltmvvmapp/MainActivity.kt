package com.rakibofc.hiltmvvmapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewModel.contactData.observe(this) {
            it.data.childrenInfo.forEach { child ->
                Log.e("TAG", "Name: ${child.name}")
            }
        }

        viewModel.fetchContactData()
    }
}