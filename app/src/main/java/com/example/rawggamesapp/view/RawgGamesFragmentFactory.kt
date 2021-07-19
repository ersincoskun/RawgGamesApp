package com.example.rawggamesapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.rawggamesapp.adapter.MasterScreenAdapter
import javax.inject.Inject

class RawgGamesFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val masterScreenAdapter: MasterScreenAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            MasterScreenFragment::class.java.name -> MasterScreenFragment(masterScreenAdapter)
            DetailScreenFragment::class.java.name -> DetailScreenFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}