package com.example.rawggamesapp.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.repo.GameRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MasterScreenViewModel @Inject constructor(
    private val repository: GameRepositoryInterface
) : ViewModel() {

    private val _gameList by lazy {
        MutableLiveData<List<Model.Game>>()
    }
    val gameList: LiveData<List<Model.Game>>
        get() = _gameList


    fun getGamesFromApi() = viewModelScope.launch {
        val gameResult = repository.getGamesFromApi()
        val gameList = gameResult.data?.results
        gameList?.let {
            insertGamesToDb(it)
        }
    }

    @VisibleForTesting
    fun insertGamesToDb(gameList: List<Model.Game>) = viewModelScope.launch {
        repository.deleteAll()
        repository.insertGames(gameList)
        _gameList.postValue(gameList)
    }

    fun getGamesFromDb() = viewModelScope.launch {
        _gameList.value = repository.getGamesFromDb()
    }

}