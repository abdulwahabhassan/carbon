package com.devhassan.data.datasource.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devhassan.data.Constants
import com.devhassan.data.api.MoviesApiService
import com.devhassan.data.model.RemoteMovie
import com.devhassan.network.util.NetworkRequestUtil
import retrofit2.HttpException

class MoviesPagingSource(
    private val moviesApiService: MoviesApiService,
    private val category: String,
) : PagingSource<Int, RemoteMovie>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, RemoteMovie> {

        val page = params.key ?: 1

        return try {
            val moviesResponse = moviesApiService.getMovies(category, page)
            LoadResult.Page(
                data = moviesResponse.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (moviesResponse.results.isEmpty()) {
                    null
                } else {
                    page + (params.loadSize / Constants.NETWORK_PAGE_SIZE)
                }
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RemoteMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}