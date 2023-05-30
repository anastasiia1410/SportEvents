package com.example.sportevents.screens.sport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportevents.databinding.FragmentSportBinding

class SportFragment : Fragment() {
    private lateinit var sportAdapter: SportAdapter
    private val viewModel by viewModels<SportViewModel>()
    private lateinit var binding: FragmentSportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sportAdapter = SportAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvSportRecycler.layoutManager = LinearLayoutManager(requireContext())
            rvSportRecycler.adapter = sportAdapter
        }
        viewModel.sportLD.observe(viewLifecycleOwner) { sportList ->
            sportAdapter.updateItems(sportList)
        }
        viewModel.loadData()


        sportAdapter.onFavoriteClick = { event ->
            viewModel.changeFavorite(event)
        }

        sportAdapter.onItemClick = {
            viewModel.changeState(it)
        }
    }
}