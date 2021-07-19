package com.example.rawggamesapp.di

import com.example.rawggamesapp.api.RetrofitApiCall
import com.example.rawggamesapp.util.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn
class AppModule {

    @Provides
    fun injectRetrofitApi():RetrofitApiCall {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApiCall::class.java)
    }
}