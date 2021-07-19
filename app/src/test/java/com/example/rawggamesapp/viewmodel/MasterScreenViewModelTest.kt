package com.example.rawggamesapp.viewmodel

import com.example.rawggamesapp.repo.FakeGameRepository
import org.junit.Before

class MasterScreenViewModelTest {

    private lateinit var masterScreenViewModel: MasterScreenViewModel

    @Before
    fun setup() {
        masterScreenViewModel = MasterScreenViewModel(FakeGameRepository())
    }

}