package com.franalarza.tryavanzado.ui.herodetail


import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.franalarza.tryavanzado.R
import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.databinding.FragmentHeroDetailBinding
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroLocation
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentHeroDetailBinding? = null
    private val viewModel: DetailViewModel by viewModels()
    private val binding get() = _binding!!
    private var googleMap: GoogleMap? = null
    private var isFavorite = false
    private lateinit var hero: HeroLocal

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
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_hero_location) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        setListeners()
        setObservers()
        viewModel.getHeroFromLocal(args.name, args.id)
        viewModel.getHeroLocation(args.id)
    }

    private fun setObservers() {
        // Detail Observer
        viewModel.liveHeroDetail.observe(viewLifecycleOwner) {
            with(binding) {
                when (it) {
                    is HeroLocalState.Success -> {
                        isFavorite = it.hero.favorite
                        hero = it.hero
                        if (it.hero.favorite) {
                            setColorFavoriteButton(Color.RED)
                        } else {
                            setColorFavoriteButton(Color.WHITE)
                        }
                        heroImageDetail.load(it.hero.photo)
                        heroNameDetail.text = it.hero.name
                        heroDescription.text = it.hero.description
                    }

                    is HeroLocalState.Failure -> {
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Location Observer
        viewModel.liveHeroLocation.observe(viewLifecycleOwner) { location ->
            when (location) {
                is LocationState.Success -> {
                    setHeroLocation(location.locations.latitud, location.locations.longitud)
                }

                is LocationState.Failure -> {
                    Toast.makeText(requireContext(), location.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setListeners() {
        binding.favoriteButton.setOnClickListener {
            viewModel.toogleFavorite(args.id)
            viewModel.toggleFavoriteLocal(args.id)
            isFavorite = !isFavorite
            if (isFavorite) {
                setColorFavoriteButton(Color.RED)
            } else {
                setColorFavoriteButton(Color.WHITE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    private fun setHeroLocation(latitude: String, longitude: String) {
        val heroLocation = LatLng(latitude.toDouble(), longitude.toDouble())
        googleMap?.addMarker(
            MarkerOptions()
                .position(heroLocation)
                .title(args.name)
        )
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(heroLocation, 10f))
    }

    private fun setColorFavoriteButton(color: Int) {
        binding.favoriteButton.setColorFilter(color)
    }

}