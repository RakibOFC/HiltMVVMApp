package com.rakibofc.hiltmvvmapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.e("TAG", "onCreate: ${someClass.doAThing()}")
        Log.e("TAG", "onCreate - Sum: ${someClass.sum()}")
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("A")
    fun provideA(): Int = 3

    @Provides
    @Named("B")
    fun provideB(): Int = 2

    @Provides
    fun provideSomeClass(@Named("A") a: Int, @Named("B") b: Int): SomeClass = SomeClass(a, b)
}

class SomeClass @Inject constructor(
    private val a: Int,
    private val b: Int
) {

    fun doAThing(): String {
        return "Look I did a thing in doAThing method in SomeClass class"
    }

    fun sum(): Int {
        return a + b
    }
}