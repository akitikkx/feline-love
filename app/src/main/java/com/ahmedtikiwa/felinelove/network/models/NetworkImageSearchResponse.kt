package com.ahmedtikiwa.felinelove.network.models

import com.ahmedtikiwa.felinelove.domain.ImageSearchItem

class NetworkImageSearchResponse : ArrayList<NetworkImageSearchResponseItem>()

fun NetworkImageSearchResponse.asDomainModel(): List<ImageSearchItem> {
    return map {
        ImageSearchItem(
            id = it.id,
            url = it.url
        )
    }
}