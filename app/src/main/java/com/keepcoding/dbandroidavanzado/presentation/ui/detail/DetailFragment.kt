package com.keepcoding.dbandroidavanzado.presentation.ui.detail


import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.keepcoding.dbandroidavanzado.R
import com.keepcoding.dbandroidavanzado.databinding.FragmentDetailBinding
import com.keepcoding.dbandroidavanzado.domain.tools.viewBinding
import com.keepcoding.dbandroidavanzado.presentation.ui.detail.model.DetailState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

       viewModel.getHero(args.heroName)
        viewModel.getLocations(args.idHero)

        viewModel.hero.observe(viewLifecycleOwner, Observer { hero ->
            for (item in hero) {

                binding.heroImageDetail.load(item.photo)
                binding.tvDetail.text = item.name
                binding.tvDescription.text = item.description

            }
            viewModel.locations.observe(viewLifecycleOwner, Observer { locations ->
                for (item in locations) {
                    binding.tvCountry.text = item.longitud
                    binding.tvLatitud.text = item.latitud
                }
            })
        })

        lifecycleScope.launch {
            viewModel.uiState.collect{ state ->

                when(state){
                    is DetailState.Loading -> loadingSettingsView()
                    is DetailState.Success -> successSettingsView()
                    is DetailState.Error -> erroSettingsView()

                }

            }
        }

    }

    private fun loadingSettingsView() {
        binding.loadingDetail.visibility = View.VISIBLE
        binding.heroImageDetail.visibility = View.GONE
        binding.tvErrorDetail.visibility = View.GONE
        binding.tvDetail.visibility = View.GONE
        binding.tvDescription.visibility = View.GONE
        binding.tvLatitud.visibility = View.GONE
        binding.tvCountry.visibility = View.GONE
        binding.buttonFavorite.visibility = View.GONE
        binding.tvLocalitation.visibility = View.GONE

    }

    private fun successSettingsView() {
        binding.loadingDetail.visibility = View.GONE
        binding.heroImageDetail.visibility = View.VISIBLE
        binding.tvErrorDetail.visibility = View.VISIBLE
        binding.tvDetail.visibility = View.VISIBLE
        binding.tvDescription.visibility = View.VISIBLE
        binding.tvLatitud.visibility = View.VISIBLE
        binding.tvCountry.visibility = View.VISIBLE
        binding.buttonFavorite.visibility = View.VISIBLE
        binding.tvLocalitation.visibility = View.VISIBLE

    }

    private fun erroSettingsView() {
        binding.loadingDetail.visibility = View.GONE
        binding.heroImageDetail.visibility = View.GONE
        binding.tvErrorDetail.visibility = View.GONE
        binding.tvDetail.visibility = View.GONE
        binding.tvDescription.visibility = View.GONE
        binding.tvLatitud.visibility = View.GONE
        binding.tvCountry.visibility = View.GONE
        binding.buttonFavorite.visibility = View.GONE
        binding.tvLocalitation.visibility = View.GONE
        binding.tvErrorDetail.visibility = View.VISIBLE
        binding.tvErrorDetail.text = "Error in app"
    }

}