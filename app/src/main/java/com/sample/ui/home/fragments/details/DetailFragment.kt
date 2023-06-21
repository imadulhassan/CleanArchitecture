package com.sample.ui.home.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sample.databinding.FragmentCharacterDetailBinding
import com.sample.extn.extractName
import com.sample.extn.loadImage
import com.sample.extn.textFromHtml
import com.sample.ui.home.HomeViewModel

class DetailFragment : Fragment() {

    private var binding: FragmentCharacterDetailBinding? = null
    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }


    private fun observeData() {
        viewModel.relatedTopic.observe(viewLifecycleOwner) {
            binding?.apply {
                characterIv.loadImage(it.icon.uRL)
                nameTv.text = extractName(it.text)
                descriptionTv.text = textFromHtml(it.result)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}