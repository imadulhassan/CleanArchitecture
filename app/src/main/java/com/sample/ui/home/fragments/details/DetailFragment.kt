package com.sample.ui.home.fragments.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sample.databinding.FragmentChracterDetailBinding
import com.sample.extn.extractName
import com.sample.extn.fromHtml
import com.sample.extn.loadPicassoImage
import com.sample.models.RelatedTopic

class DetailFragment : Fragment() {

    private var binding: FragmentChracterDetailBinding? = null

    private val viewModel: DetailViewModel by activityViewModels()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val obj = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("OBJ", RelatedTopic::class.java)
            } else {
                bundle.getParcelable("OBJ")
            }
            obj?.let {
                viewModel.putValue(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChracterDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }


    private fun observeData() {
        viewModel.item.observe(viewLifecycleOwner) {
            setData(it)
        }
    }

    private fun setData(item: RelatedTopic) {
        item.let { relatedTopic ->
            item.icon.let {
                binding?.ivUser?.loadPicassoImage(it.uRL)
            }
            binding?.tvName?.text = extractName(relatedTopic.text)
            binding?.tvDescription?.text = fromHtml(relatedTopic.result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
    }

}