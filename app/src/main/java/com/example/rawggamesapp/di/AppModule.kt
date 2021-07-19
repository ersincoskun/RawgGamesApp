package com.example.rawggamesapp.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rawggamesapp.R
import com.example.rawggamesapp.api.RetrofitApiCall
import com.example.rawggamesapp.database.dao.GameDao
import com.example.rawggamesapp.database.db.GameDb
import com.example.rawggamesapp.repo.GameRepository
import com.example.rawggamesapp.repo.GameRepositoryInterface
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
        Room.databaseBuilder(context, GameDb::class.java, "gameDb").fallbackToDestructiveMigrationFrom(1).build()

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

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_game_place_holder).error(R.drawable.ic_error)
        )

    @Singleton
    @Provides
    fun injectRepository(dao: GameDao, api: RetrofitApiCall) =
        GameRepository(dao, api) as GameRepositoryInterface

}