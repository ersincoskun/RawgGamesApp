package com.example.rawggamesapp.di

import android.content.Context
import androidx.room.Room
import com.example.rawggamesapp.api.RetrofitApiCall
import com.example.rawggamesapp.database.db.GameDb
import com.example.rawggamesapp.util.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GameDb::class.java, "gameDb").build()

    @Singleton
    @Provides
    fun injectDao(database: GameDb) = database.gameDao()

    @Singleton
    @Provides
    fun injectRetrofitApi(): RetrofitApiCall {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApiCall::class.java)
    }
}