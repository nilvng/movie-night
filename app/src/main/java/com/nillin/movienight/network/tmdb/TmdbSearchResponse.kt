package com.nillin.movienight.network.tmdb

import com.squareup.moshi.Json

data class TmdbSearchResponse(
    val page: Int,
    val results: List<TmdbSeachResult>,
    @Json(name = "total_pages")
    val totalPages: Int,
)

data class TmdbSeachResult(
    val id: Int,
    @Json(name="name")
    val _name: String? = null,
    @Json(name="title")
    val _title: String? = null,
    val overview: String,
    @Json(name = "poster_path")
    val imgSrcPath: String?,
    @Json(name = "first_air_date")
    val firstAirDate: String?
) {
    val name = _name ?: _title
}