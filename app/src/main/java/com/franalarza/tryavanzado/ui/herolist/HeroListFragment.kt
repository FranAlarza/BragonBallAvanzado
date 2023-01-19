package com.franalarza.tryavanzado.ui.herolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.franalarza.tryavanzado.databinding.FragmentHeroListBinding
import com.franalarza.tryavanzado.domain.Hero
import com.franalarza.tryavanzado.ui.commons.HeroListAdapter
import java.util.UUID

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!

    private val adapter = HeroListAdapter()
    private  val viewModel: HeroListViewModel by viewModels()

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
            adapter.submitList(getHero(20))
        }

        binding.faboton.setOnClickListener {
            viewModel.tareaCostosa()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getHero(size: Int): List<Hero> {
        val listHero = mutableListOf<Hero>()

        for (i in 0..size) {
            val hero = Hero(UUID.randomUUID().toString(), "hero-$i", "", "photo-$i")
            listHero.add(hero)
        }

        return listHero
    }
}