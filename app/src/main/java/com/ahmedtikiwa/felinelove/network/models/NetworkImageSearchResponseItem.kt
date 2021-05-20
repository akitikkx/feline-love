package com.ahmedtikiwa.felinelove.network.models

data class NetworkImageSearchResponseItem(
    val breeds: List<Any>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)