package com.example.rawggamesapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggamesapp.api.RetrofitApiCall
import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.util.util.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MasterScreenViewModel @Inject constructor(
    private val api: RetrofitApiCall,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _gameList by lazy {
        MutableLiveData<List<Model.Game>>()
    }

    val gameList: LiveData<List<Model.Game>>
        get() = _gameList

    fun getGamesFromApi() {
        viewModelScope.launch {
            val call = api.getGames(API_KEY)
            call.enqueue(object : Callback<Model.GameResult> {
                override fun onResponse(
                    call: Call<Model.GameResult>,
                    response: Response<Model.GameResult>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _gameList.value = it.results
                        }
                    }
                }

                override fun onFailure(call: Call<Model.GameResult>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Please check your network connection",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}