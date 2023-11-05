package com.nillin.movienight.network.tmdb

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


 const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
private val TMDB_API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzY2Y2MzNjZTFiZjU0ZjhmNjU1MjljZGNiMjBkNWJiNSIsInN1YiI6IjY0OGQ3YzllNTU5ZDIyMDExYzRiZWY1ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cm7Wlo0SIYyGfo4XPoyueQccBHyqOxacCNWaP0AzFno"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(TMDB_BASE_URL)
    .client(OkHttpClient.Builder().addInterceptor { chain ->
        val headerRequest = chain.request().newBuilder().header("Authorization", "Bearer $TMDB_API_KEY").build()
        chain.proceed(headerRequest)
    }.build())
    .build()

interface TmdbApiService {
    @GET("search/multi")
    suspend fun searchMovies(@Query("query") query: String): TmdbSearchResponse
}

object TmdbApi {
    val retrofitService: TmdbApiService by lazy {
        retrofit.create(TmdbApiService::class.java)
    }
}

