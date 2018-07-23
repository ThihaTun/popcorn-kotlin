package com.example.thiha.popcorn_kotlin.app

import com.example.thiha.popcorn_kotlin.main.MainActivity
import com.example.thiha.popcorn_kotlin.main.MainModule
import com.example.thiha.popcorn_kotlin.main.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity


}