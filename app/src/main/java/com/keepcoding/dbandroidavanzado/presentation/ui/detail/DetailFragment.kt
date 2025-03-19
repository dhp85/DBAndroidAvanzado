package com.keepcoding.dbandroidavanzado.presentation.ui.detail


import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import com.keepcoding.dbandroidavanzado.R
import com.keepcoding.dbandroidavanzado.databinding.FragmentDetailBinding
import com.keepcoding.dbandroidavanzado.domain.tools.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

       viewModel.getHero(args.heroName)

        viewModel.hero.observe(viewLifecycleOwner, Observer { hero ->
            for (item in hero) {

                binding.heroImageDetail.load(item.photo)
                binding.tvDetail.text = item.name
                binding.tvDescription.text = item.description
            }
        })

    }

}