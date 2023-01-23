package com.franalarza.tryavanzado.ui.herodetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.franalarza.tryavanzado.R
import com.franalarza.tryavanzado.databinding.FragmentHeroDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentHeroDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            heroImageDetail.load(args.hero.photo)
            heroNameDetail.text = args.hero.name
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}