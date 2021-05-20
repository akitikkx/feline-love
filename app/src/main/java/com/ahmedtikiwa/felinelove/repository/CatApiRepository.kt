package com.ahmedtikiwa.felinelove.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ahmedtikiwa.felinelove.network.CatApiNetwork

class CatApiRepository {

    fun getImages() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CatApiPagingSource(CatApiNetwork.catApi) }
        ).liveData
}