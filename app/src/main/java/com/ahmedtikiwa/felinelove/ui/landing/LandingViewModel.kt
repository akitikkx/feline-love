package com.ahmedtikiwa.felinelove.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ahmedtikiwa.felinelove.repository.CatApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    repository: CatApiRepository
) : ViewModel() {

    val catResults = repository.getImages().cachedIn(viewModelScope)
}