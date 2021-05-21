package com.ahmedtikiwa.felinelove.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.ahmedtikiwa.felinelove.R
import com.ahmedtikiwa.felinelove.databinding.FragmentLandingBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LandingFragment : Fragment(), CatResultsAdapter.CatResultsAdapterListener {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LandingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        val adapter = CatResultsAdapter(this)

        binding.apply {
            catList.setHasFixedSize(true)
            catList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CatResultsLoadStateAdapter { adapter.retry() },
                footer = CatResultsLoadStateAdapter { adapter.retry() }
            )
            catList.layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.SPACE_EVENLY
            }
        }

        viewModel.catResults.observe(viewLifecycleOwner, { pagingData ->
            adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.cat_list_loading_message),
                        Snackbar.LENGTH_LONG
                    ).show()
                } else if (loadState.refresh is LoadState.Error) {
                    val error = (loadState.refresh as LoadState.Error).error.message
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.cat_list_loading_error, error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onImageClick(view: View, url: String) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        val transitionName = getString(R.string.cat_item_transition_name)

        val directions = LandingFragmentDirections.actionLandingFragmentToDetailFragment(url)
        findNavController().navigate(directions, FragmentNavigatorExtras(view to transitionName))
    }
}