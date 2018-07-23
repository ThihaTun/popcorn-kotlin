package com.example.thiha.popcorn_kotlin.app

import android.app.Application
import com.example.thiha.popcorn_kotlin.api.ApiModule
import com.example.thiha.popcorn_kotlin.database.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class,
    AppModule::class, ApiModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(app: MovieApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}