package com.rakibofc.hiltmvvmapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var exampleClass1: ExampleClass1

    @Inject
    lateinit var exampleClass2: ExampleClass2

    @Inject
    lateinit var exampleClass4: ExampleClass4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        /*Log.e("TAG", "exampleClass1 - doAThing: ${exampleClass1.doAThing()}")
        Log.e("TAG", "exampleClass1 - sum: ${exampleClass1.sum()}")
        Log.e(
            "TAG",
            "exampleClass2 - exampleMethod2 with exampleMethod3: ${exampleClass2.exampleMethod2()}"
        )*/

        Log.e("TAG", "exampleClass4 - exampleMethod4: ${exampleClass4.exampleMethod4()}")
    }
}

/* --------------------------------------------------------------------------------- */
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
    fun provideSomeClass(@Named("A") a: Int, @Named("B") b: Int): ExampleClass1 =
        ExampleClass1(a, b)
}

class ExampleClass1 @Inject constructor(
    private val a: Int,
    private val b: Int
) {

    fun doAThing(): String {
        return "Look I did a thing in doAThing method in ExampleClass1"
    }

    fun sum(): Int {
        return a + b
    }
}

/* --------------------------------------------------------------------------------- */
class ExampleClass2 @Inject constructor(
    private val exampleClass3: ExampleClass3
) {
    fun exampleMethod2(): String {
        return "ExampleMethod2 in ExampleClass2 : ${exampleClass3.exampleMethod3()}"
    }
}

class ExampleClass3 @Inject constructor() {

    fun exampleMethod3(): String {
        return "ExampleMethod3 in ExampleClass3"
    }
}

/* --------------------------------------------------------------------------------- */
// This section will create compile time error
class ExampleClass4 @Inject constructor(
    private val exampleInterface1: ExampleInterface1
) {
    fun exampleMethod4(): String {
        return "ExampleMethod4 in ExampleClass4 : ${exampleInterface1.interfaceMethod1()}"
    }
}

class ExampleInterface1Impl
@Inject
constructor(
    private val exampleModule2Method: String
) : ExampleInterface1 {
    override fun interfaceMethod1(): String {
        return "ExampleInterface2Impl: $exampleModule2Method"
    }
}

interface ExampleInterface1 {
    fun interfaceMethod1(): String
}

/* ------- Binds or Provides: Binds ------- */
/*@Module
@InstallIn(SingletonComponent::class)
*//* @InstallIn(ActivityComponent::class) *//*
abstract class ExampleModule1 {

    @Singleton
    *//* @ActivityScoped *//*
    @Binds
    abstract fun bindExampleInterface1(impl: ExampleInterface1Impl): ExampleInterface1
}*/
/* ------ Binds or Provides: Provides ------ */
@Module
@InstallIn(SingletonComponent::class)
class ExampleModule2 {

    @Provides
    @Singleton
    fun provideExampleInterface1(): ExampleInterface1 {
        return ExampleInterface1Impl(exampleModule2Method())
    }

    @Provides
    @Singleton
    fun exampleModule2Method(): String {
        return "exampleModule2Method"
    }
}
/* --------------------------------------------------------------------------------- */