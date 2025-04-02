package com.keepcoding.dbandroidavanzado.presentation.ui.heros


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.keepcoding.dbandroidavanzado.databinding.FragmentHomeBinding
import com.keepcoding.dbandroidavanzado.presentation.ui.heros.model.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = HomeAdapter {
            val action =
                HomeFragmentDirections.actionNavigationHomeToNavigationDetail(it.name, it.id)
            findNavController().navigate(action)
        }

        binding.heroList.adapter = adapter
        viewModel.getSuperHeros()

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.state.collect { state ->
                when (state) {
                    is HomeState.Loading -> loadingSettingsView()
                    is HomeState.Success -> {
                        adapter.updateList(state.heros)
                        successSettingsView()
                    }

                    is HomeState.Error -> erroSettingsView()

                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearViewModel()
        _binding = null

    }

    private fun loadingSettingsView() {
        with(binding) {
            loading.visibility = View.VISIBLE
            heroList.visibility = View.GONE
        }


    }

    private fun successSettingsView() {
        with(binding) {
            loading.visibility = View.GONE
            heroList.visibility = View.VISIBLE
        }


    }

    private fun erroSettingsView() {
        with(binding) {
            loading.visibility = View.GONE
            heroList.visibility = View.GONE
            tvError.visibility = View.VISIBLE
            tvError.text = "Error in app"
        }
    }
}