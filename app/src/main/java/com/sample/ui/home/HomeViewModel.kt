package com.sample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.models.RelatedTopic
import com.sample.network.Status
import com.sample.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val uiStateMld = MutableLiveData(UiState())
    private val dataListMld = MutableLiveData<List<RelatedTopic>>()
    private val relatedTopicMld = MutableLiveData<RelatedTopic>()
    internal val uiState: LiveData<UiState> get() = uiStateMld
    val dataList: LiveData<List<RelatedTopic>> get() = dataListMld
    val relatedTopic: LiveData<RelatedTopic> get() = relatedTopicMld


    init {
        getList()
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        uiStateMld.postValue(UiState(isLoading = true))
        val response = mainRepository.getCharactersData()
        var message = ""
        when (response.status) {
            Status.SUCCESS -> dataListMld.postValue(response.data?.relatedTopics.orEmpty())
            Status.ERROR -> message = response.message.toString()
        }
        uiStateMld.postValue(UiState(isLoading = false, message))
    }

    fun setSelectedItem(relatedTopic: RelatedTopic) {
        setRelatedTopic(relatedTopic)
    }

    private fun setRelatedTopic(relatedTopic: RelatedTopic) {
        relatedTopicMld.postValue(relatedTopic)
    }

}
