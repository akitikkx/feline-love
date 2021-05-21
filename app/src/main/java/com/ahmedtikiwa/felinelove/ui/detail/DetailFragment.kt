package com.ahmedtikiwa.felinelove.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmedtikiwa.felinelove.R
import com.ahmedtikiwa.felinelove.databinding.FragmentDetailBinding
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var detailViewModelFactory: DetailViewModel.DetailViewModelFactory

    private val viewModel by viewModels<DetailViewModel> {
        detailViewModelFactory.create(this, args.url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share -> {
                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_intent_text, args.url))
                    type = "text/plain"
                }
                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        null
                    )
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}