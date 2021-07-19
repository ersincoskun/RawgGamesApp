package com.example.rawggamesapp.viewmodel

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
class DetailScreenViewModel @Inject constructor(
    private val repository: GameRepositoryInterface
) : ViewModel() {

    private val _game by lazy {
        MutableLiveData<Model.Game>()
    }
    val game: LiveData<Model.Game>
        get() = _game

    fun getGame(gameId: Int) {
        viewModelScope.launch {
            _game.value = repository.getGameFromDb(gameId)
        }
    }

}