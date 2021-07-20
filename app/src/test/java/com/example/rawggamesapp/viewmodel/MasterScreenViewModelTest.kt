package com.example.rawggamesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rawggamesapp.MainCoroutineRule
import com.example.rawggamesapp.getOrAwaitValueTest
import com.example.rawggamesapp.repo.FakeGameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat


@ExperimentalCoroutinesApi
class MasterScreenViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var masterScreenViewModel: MasterScreenViewModel

    @Before
    fun setup() {
        masterScreenViewModel = MasterScreenViewModel(FakeGameRepository())
    }

    @Test
    fun `getGamesFromApi fun should insert game data`() {
        masterScreenViewModel.getGamesFromApi()
        val value = masterScreenViewModel.gameList.getOrAwaitValueTest()
        assertThat(value).hasSize(1)
    }

    @Test
    fun `first it will get data from db then refresh data from api`() {
        masterScreenViewModel.getGamesFromDb()
        val value = masterScreenViewModel.gameList.getOrAwaitValueTest()
        assertThat(value.first().gameId).isEqualTo(0)
        masterScreenViewModel.getGamesFromApi()
        val value2=masterScreenViewModel.gameList.getOrAwaitValueTest()
        assertThat(value2.first().gameId).isEqualTo(1)
    }

}