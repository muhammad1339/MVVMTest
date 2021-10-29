package com.proprog.applicationtest.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.proprog.applicationtest.data.model.Item
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 10

class StackDataSource(private val repo: StackRepository, private val site: String) :
    PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                repo.getStackAnswers(position.toString(), NETWORK_PAGE_SIZE.toString(), site)
            val nextKey = if (!response.hasMore) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = response.items,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}