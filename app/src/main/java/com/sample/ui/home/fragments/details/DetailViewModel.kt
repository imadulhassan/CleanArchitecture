package com.sample.ui.home.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.base.CommonViewModel
import com.sample.models.RelatedTopic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : CommonViewModel() {

    private val singleItem = MutableLiveData<RelatedTopic>()
    val item:LiveData<RelatedTopic> get() = singleItem

    fun putValue(item: RelatedTopic) {
        singleItem.postValue(item)
    }

}


