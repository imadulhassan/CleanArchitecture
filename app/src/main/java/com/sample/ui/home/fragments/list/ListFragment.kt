package com.sample.ui.home.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.R
import com.sample.adapters.CharacterAdapter
import com.sample.databinding.FragmentChracterListBinding
import com.sample.extn.hide
import com.sample.extn.show
import com.sample.extn.showToast
import com.sample.models.ApisResponse
import com.sample.models.RelatedTopic
import com.sample.ui.home.WorkingStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var binding: FragmentChracterListBinding? = null

    private val viewModel: ListViewModel by viewModels()

    private val characterAdapter by lazy {
        CharacterAdapter { position: Int ->
            Log.d("List", ": $position ")
            viewModel.onClickItem(position)?.let { goToDetailsScreen(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChracterListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvAdapaters()
        setSearchListener()
        observeData()

    }

    private  fun setSearchListener(){
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                characterAdapter.filterList(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                characterAdapter.filterList(newText)
                return true
            }
        })
    }

    private fun setRvAdapaters() {
        binding?.rvCharachterList?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvCharachterList?.adapter = characterAdapter
    }

    private fun observeData() {
        viewModel.getResult<ApisResponse>()?.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.processResult(it.data as ApisResponse)
            }
        }
        viewModel.getMessage().observe(viewLifecycleOwner) {
            if (isVisible) {
                requireActivity().showToast(it)
            }
        }
        viewModel.itemsList.observe(viewLifecycleOwner) {
            characterAdapter.updateList(it as ArrayList<RelatedTopic>)
        }
        viewModel.screenState.observe(viewLifecycleOwner) { status ->
            if (isVisible) {
                when (status.state) {
                    WorkingStatus.Loaded -> {
                        binding?.showLoading?.root?.hide()
                    }
                    WorkingStatus.Loading -> {
                        binding?.showLoading?.root?.show()
                    }
                }
            }
        }
    }

    private fun goToDetailsScreen(item: RelatedTopic) {
        item.let {
            val itemDetailFragmentContainer: View? =
                binding?.root?.findViewById(R.id.fragment_detail_container)
            val bundle = Bundle()
            bundle.putParcelable("OBJ", item)
            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_chracter_detail, bundle)
            } else findNavController().navigate(R.id.show_chracter_detail, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
    }
}
