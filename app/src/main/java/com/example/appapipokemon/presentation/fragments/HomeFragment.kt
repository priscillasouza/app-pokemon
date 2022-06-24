package com.example.appapipokemon.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appapipokemon.databinding.FragmentHomeBinding
import com.example.appapipokemon.presentation.adapter.PokemonAdapter
import com.example.appapipokemon.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterRecyclerViewListPokemons: PokemonAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.listPokemons(null)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserver()
        startAdapter()
    }

    private fun onObserver() {
        homeViewModel.apply {
            pokemonResponse.observe(viewLifecycleOwner) { response ->
                adapterRecyclerViewListPokemons.setList(response.results)
            }

            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Falha na lista de pokemons", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun startAdapter() {
        binding.recyclerViewListPokemons.apply {
            adapterRecyclerViewListPokemons = PokemonAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterRecyclerViewListPokemons
        }
    }
}