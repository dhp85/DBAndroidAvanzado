package com.keepcoding.dbandroidavanzado.presentation.ui.detail


import android.content.Context
import android.os.Bundle

import android.view.View
import androidx.core.content.ContextCompat
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
        isFavorite()

        viewModel.hero.observe(viewLifecycleOwner, Observer { hero ->
            for (item in hero) {

                binding.heroImageDetail.load(item.photo)
                binding.tvDetail.text = item.name
                binding.tvDescription.text = item.description
                if (item.favorite) {
                    binding.buttonFavorite.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red)
                }else{
                    binding.buttonFavorite.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.grey)
                }

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

    private fun isFavorite(){
        binding.buttonFavorite.setOnClickListener {
            viewModel.isFavorite(args.idHero)
        }
    }

    private fun loadingSettingsView() {

        with(binding) {
            loadingDetail.visibility = View.VISIBLE
            heroImageDetail.visibility = View.GONE
            tvErrorDetail.visibility = View.GONE
            tvDetail.visibility = View.GONE
            tvDescription.visibility = View.GONE
            tvLatitud.visibility = View.GONE
            tvCountry.visibility = View.GONE
            buttonFavorite.visibility = View.GONE
            tvLocalitation.visibility = View.GONE
        }
    }

    private fun successSettingsView() {
        with(binding) {
            loadingDetail.visibility = View.GONE
            heroImageDetail.visibility = View.VISIBLE
            tvErrorDetail.visibility = View.VISIBLE
            tvDetail.visibility = View.VISIBLE
            tvDescription.visibility = View.VISIBLE
            tvLatitud.visibility = View.VISIBLE
            tvCountry.visibility = View.VISIBLE
            buttonFavorite.visibility = View.VISIBLE
            tvLocalitation.visibility = View.VISIBLE
        }
    }

    private fun erroSettingsView() {
        with(binding) {
            loadingDetail.visibility = View.GONE
            heroImageDetail.visibility = View.GONE
            tvErrorDetail.visibility = View.GONE
            tvDetail.visibility = View.GONE
            tvDescription.visibility = View.GONE
            tvLatitud.visibility = View.GONE
            tvCountry.visibility = View.GONE
            buttonFavorite.visibility = View.GONE
            tvLocalitation.visibility = View.GONE
            tvErrorDetail.visibility = View.VISIBLE
            tvErrorDetail.text = "Error in app"
        }
    }
}