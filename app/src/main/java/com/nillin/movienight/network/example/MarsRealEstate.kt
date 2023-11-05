package com.nillin.movienight.network.example

import com.squareup.moshi.Json

data class MarsRealEstate(
    val id: String,
    val price: Int,
    val type: MarsRealEstateType,
    @Json(name = "img_src") val imgSrcUrl: String
)

enum class MarsRealEstateType {
    @Json(name = "rent")
    RENT,
    @Json(name = "buy")
    BUY
}