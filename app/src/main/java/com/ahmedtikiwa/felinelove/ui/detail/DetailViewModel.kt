package com.ahmedtikiwa.felinelove.ui.detail

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    url: String
) : ViewModel() {

    private var _url =  MutableLiveData<String>()
    val url: LiveData<String> = _url

    init {
        savedStateHandle.set(CAT_IMAGE_URL, url)
        _url.postValue(savedStateHandle.get(CAT_IMAGE_URL))
    }

    @AssistedFactory
    interface DetailViewModelFactory {
        fun create(owner: SavedStateRegistryOwner, url: String): Factory
    }

    companion object {
        const val CAT_IMAGE_URL = "catImageUrl"
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @AssistedInject constructor(
        @Assisted owner: SavedStateRegistryOwner,
        @Assisted private val url: String
    ) : AbstractSavedStateViewModelFactory(owner, null) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return DetailViewModel(handle, url) as T
        }
    }
}