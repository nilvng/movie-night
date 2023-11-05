package com.nillin.movienight.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private const val MARS_BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(MARS_BASE_URL)
    .build()
interface MarksApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>

    @GET("realestate")
    suspend fun getRealEstate(): List<MarsRealEstate>
}

object MarksApi {
    val retrofitService : MarksApiService by lazy {
        retrofit.create(MarksApiService::class.java) }
}