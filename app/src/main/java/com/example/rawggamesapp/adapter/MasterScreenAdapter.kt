package com.example.rawggamesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.rawggamesapp.databinding.GameItemBinding
import com.example.rawggamesapp.model.Model
import com.example.rawggamesapp.view.MasterScreenFragmentDirections
import javax.inject.Inject

class MasterScreenAdapter @Inject constructor(
    private val glide: RequestManager
) :
    RecyclerView.Adapter<MasterScreenAdapter.MasterScreenViewHolder>() {

    val games = mutableListOf<Model.Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterScreenViewHolder {
        val itemBinding =
            GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MasterScreenViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MasterScreenViewHolder, position: Int) {
        holder.itemBinding.gameNameTV.text = games[position].name
        glide.load(games[position].imageUrl).into(holder.itemBinding.gameIV)
        holder.itemBinding.gameItemCardView.setOnClickListener {
            val action =
                MasterScreenFragmentDirections.actionMasterScreenFragmentToDetailScreenFragment(
                    games[position].gameId
                )
            Navigation.findNavController(holder.itemBinding.gameItemCardView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class MasterScreenViewHolder(val itemBinding: GameItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}