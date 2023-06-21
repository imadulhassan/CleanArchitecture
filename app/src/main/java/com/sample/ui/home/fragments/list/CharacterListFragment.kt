package com.sample.ui.home.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.R
import com.sample.databinding.FragmentCharacterListBinding
import com.sample.extn.hide
import com.sample.extn.show
import com.sample.extn.showToast
import com.sample.models.RelatedTopic
import com.sample.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var binding: FragmentCharacterListBinding? = null
    private val viewModel by activityViewModels<HomeViewModel>()
    private val characterAdapter by lazy { CharacterAdapter { goToDetailsScreen(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvAdapaters()
        setSearchListener()
        observeData()
    }

    private fun setSearchListener() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                characterAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                characterAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun setRvAdapaters() {
        binding?.characterListRv?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    private fun observeData() {
        viewModel.dataList.observe(viewLifecycleOwner) {
            characterAdapter.updateList(it )
        }
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (isVisible) {
                if (uiState.isLoading) binding?.showLoading?.root?.show() else binding?.showLoading?.root?.hide()
                uiState.errorMessage.takeIf { it.isBlank().not() }
                    ?.apply { context?.showToast(this@apply) }
            }
        }
    }

    private fun goToDetailsScreen(relatedTopic: RelatedTopic) {
        viewModel.setSelectedItem(relatedTopic)
        val itemDetailFragmentContainer: View? =
            binding?.root?.findViewById(R.id.detailFragmentContainer)
        if (itemDetailFragmentContainer != null) {
            itemDetailFragmentContainer.findNavController().navigate(R.id.detailFragment)
        } else
            findNavController().navigate(R.id.show_chracter_detail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
