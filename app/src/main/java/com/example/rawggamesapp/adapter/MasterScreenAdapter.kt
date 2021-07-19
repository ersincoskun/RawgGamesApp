package com.example.rawggamesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.databinding.GameItemBinding
import com.example.rawggamesapp.model.Model
import javax.inject.Inject

class MasterScreenAdapter @Inject constructor() : RecyclerView.Adapter<MasterScreenAdapter.MasterScreenViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Model.Game>() {
        override fun areItemsTheSame(oldItem: Model.Game, newItem: Model.Game): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Model.Game, newItem: Model.Game): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var games: List<Model.Game>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterScreenViewHolder {
        val itemBinding =
            GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MasterScreenViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MasterScreenViewHolder, position: Int) {
        holder.itemBinding.gameButton.text = games[position].name
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class MasterScreenViewHolder(val itemBinding: GameItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}