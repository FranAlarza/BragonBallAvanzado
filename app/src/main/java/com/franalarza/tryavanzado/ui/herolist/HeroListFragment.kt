package com.franalarza.tryavanzado.ui.herolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.franalarza.tryavanzado.databinding.FragmentHeroListBinding
import com.franalarza.tryavanzado.ui.commons.HeroListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!

    private val adapter = HeroListAdapter {
        Log.d("Nombre Hero", it.toString())

        findNavController().navigate(HeroListFragmentDirections.actionHeroListFragmentToDetailFragment(it.id, it.name))
    }
    private  val viewModel: HeroesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            heroList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            heroList.adapter = adapter
            viewModel.liveHeroes.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is HeroesState.Success -> {
                        adapter.submitList(state.heroes)
                        Log.d("Local", state.heroes.toString())
                    }

                    is HeroesState.Failure -> {
                        Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }

            }
            viewModel.getHeroes()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}