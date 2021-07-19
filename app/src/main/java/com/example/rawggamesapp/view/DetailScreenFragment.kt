package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.rawggamesapp.databinding.FragmentDetailScreenBinding
import com.example.rawggamesapp.viewmodel.DetailScreenViewModel
import javax.inject.Inject

class DetailScreenFragment @Inject constructor(
    val glide: RequestManager
) : Fragment() {

    private var _binding: FragmentDetailScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailScreenViewModel: DetailScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailScreenViewModel =
            ViewModelProvider(requireActivity()).get(DetailScreenViewModel::class.java)
        arguments?.let {
            val args = DetailScreenFragmentArgs.fromBundle(it)
            observeData(args.gameId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData(gameId: Int) {
        detailScreenViewModel.getGame(gameId)
        detailScreenViewModel.game.observe(viewLifecycleOwner, Observer {
            glide.load(it.imageUrl).into(binding.gameIV)
            binding.gameNameTV.text = "Game Name: ${it.name}"
            binding.gameReleasedTV.text = "Released: ${it.released}"
            binding.gameRatingTV.text = "Rating: ${it.rating}"
            binding.gamePlaytimeTV.text = "Play Time: ${it.playtime}"
            binding.updatedTV.text="Updated: ${it.updated}"
            binding.metacriticTV.text="Metacritic: ${it.metacritic}"
            binding.addedTV.text="Added: ${it.added}"
        })
    }

}