package com.example.rawggamesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rawggamesapp.MainCoroutineRule
import com.example.rawggamesapp.getOrAwaitValueTest
import com.example.rawggamesapp.repo.FakeGameRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailScreenViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var detailScreenViewModel: DetailScreenViewModel

    @Before
    fun setup() {
        detailScreenViewModel = DetailScreenViewModel(FakeGameRepository())
    }

    @Test
    fun getGameTest() {
        detailScreenViewModel.getGame(2)
        val value = detailScreenViewModel.game.getOrAwaitValueTest()
        Truth.assertThat(value.gameId).isEqualTo(2)
    }


}