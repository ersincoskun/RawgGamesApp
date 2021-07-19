package com.example.rawggamesapp.view

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface RawgGameFragmentFactoryEntryPoint {

    fun getFragmentFactory(): RawgGamesFragmentFactory

}