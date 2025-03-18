package com.keepcoding.dbandroidavanzado.presentation.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.keepcoding.dbandroidavanzado.R
import com.keepcoding.dbandroidavanzado.databinding.FragmentDetailBinding
import com.keepcoding.dbandroidavanzado.databinding.FragmentHomeBinding
import com.keepcoding.dbandroidavanzado.domain.tools.viewBinding
import com.keepcoding.dbandroidavanzado.presentation.ui.heros.model.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvDetail.text = "Hola"
        }
    }

}