package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rawggamesapp.adapter.MasterScreenAdapter
import com.example.rawggamesapp.databinding.FragmentMasterScreenBinding
import com.example.rawggamesapp.viewmodel.MasterScreenViewModel
import javax.inject.Inject

class MasterScreenFragment @Inject constructor(
    private val masterScreenAdapter: MasterScreenAdapter
) : Fragment() {

    private var _binding: FragmentMasterScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var masterScreenViewModel: MasterScreenViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMasterScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        masterScreenViewModel =
            ViewModelProvider(requireActivity()).get(MasterScreenViewModel::class.java)
        binding.masterScreenRV.adapter = masterScreenAdapter
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() {
        masterScreenViewModel.getGamesFromDb()
        masterScreenViewModel.gameList.observe(viewLifecycleOwner, Observer {
            masterScreenAdapter.games=it
        })
    }

}