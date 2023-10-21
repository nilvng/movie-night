package com.nillin.movienight.local.ratings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val movie_id: Int,
    val user_id: Int,
    val rating: Int,
    val created_at: Long,
    val note: String?,
)

val Rating.tier: String get() = when (rating) {
    in 0..2 -> "bad"
    in 3..5 -> "alright"
    in 6..8 -> "good"
    in 9..10 -> "god-tier"
    else -> "unknown"
}