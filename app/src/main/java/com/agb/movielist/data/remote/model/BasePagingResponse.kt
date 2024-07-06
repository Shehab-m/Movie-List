package com.agb.movielist.data.remote.model

import com.google.gson.annotations.SerializedName

data class BasePagingResponse<T>(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<T>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
)