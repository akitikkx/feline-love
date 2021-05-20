package com.ahmedtikiwa.felinelove.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmedtikiwa.felinelove.domain.ImageSearchItem
import com.ahmedtikiwa.felinelove.network.CatApiService
import com.ahmedtikiwa.felinelove.network.models.NetworkImageSearchResponseItem
import com.ahmedtikiwa.felinelove.network.models.asDomainModel
import retrofit2.HttpException
import java.io.IOException

class CatApiPagingSource(private val catApi: CatApiService) : PagingSource<Int, ImageSearchItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageSearchItem> {
        val currentPosition = params.key ?: CAT_API_START_INDEX

        return try {
            val searchResponse = catApi.getImagesAsync(true, 10, currentPosition).await()
            val items = searchResponse.asDomainModel()

            LoadResult.Page(
                data = items,
                prevKey = if (currentPosition == CAT_API_START_INDEX) null else currentPosition - 1,
                nextKey = if (items.isEmpty()) null else currentPosition + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageSearchItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        const val CAT_API_START_INDEX = 1
    }
}