package com.rakibofc.hiltmvvmapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.e("TAG", "onCreate: ${someClass.doAThing()}")
        Log.e("TAG", "onCreate - Sum: ${someClass.sum(3, 2)}")
        Log.e("TAG", "onCreate: ${someClass.doSomeOtherThing()}")
        Log.e("TAG", "onCreate: ${someClass.multiply(3, 2)}")
    }
}

class SomeClass @Inject constructor(
    private val someOtherClass: SomeOtherClass
) {

    fun doAThing(): String {
        return "Look I did a thing in doAThing method in SomeClass class"
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun doSomeOtherThing(): String {
        return someOtherClass.doSomeOtherThing()
    }

    fun multiply(a: Int, b: Int): Int {
        return someOtherClass.multiply(a, b)
    }
}

class SomeOtherClass @Inject constructor() {

    fun doSomeOtherThing(): String {
        return "Look I did a thing in doSomeOtherThing method in SomeOtherClass class"
    }

    fun multiply(a: Int, b: Int): Int {
        return a * b
    }
}