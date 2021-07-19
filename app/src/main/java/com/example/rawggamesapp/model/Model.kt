package com.example.rawggamesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Model {

    data class GameResult(
        val count:Int,
        val results:List<Game>
    )

    @Entity(tableName = "games")
    data class Game(
        @SerializedName("id")
        val gameId:Int,
        val name:String,
        val released:String,
        @SerializedName("background_image")
        val imageUrl:String,
        val rating:Int,
        val playtime:Int,
        @PrimaryKey(autoGenerate = true)
        var uuid: Long? = null
    )

}