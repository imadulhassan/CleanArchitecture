package com.sample.ui.home.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.base.CommonViewModel
import com.sample.models.ApisResponse
import com.sample.models.RelatedTopic
import com.sample.repo.MainRepository
import com.sample.ui.home.HomeScreenState
import com.sample.ui.home.WorkingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val mainRepository: MainRepository) : CommonViewModel() {

    private val homeScreenState = MutableLiveData(HomeScreenState(WorkingStatus.Loading))
    val screenState: LiveData<HomeScreenState> get() = homeScreenState

    private val topicList = MutableLiveData<List<RelatedTopic>>()
    val itemsList: LiveData<List<RelatedTopic>> get() = topicList

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRepository.getCharacters()
            processResponse(response)
            homeScreenState.postValue(HomeScreenState(WorkingStatus.Loaded))
        }
    }

    fun processResult(response: ApisResponse) {
        val relatedTopics = response.relatedTopics
        topicList.postValue(relatedTopics)
    }

}
