package com.nillin.movienight.local.movie

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val synopsis: String,
    val cover: String,
    val creator: String,
    val actors: String,
    val year: Int,
    val genre: String,
        )

@Entity
data class MovieDetail (
    @Embedded val movie: Movie,
    val rating: Int,
    val popularity: Int,
    val bookmark: Int,
        )

fun Movie.asState(): com.nillin.movienight.state.MovieUI {
    return com.nillin.movienight.state.MovieUI(
        id = this.id,
        title = this.title,
        cover = this.cover,
        creator = this.creator,
        actors = this.actors.split(","),
        year = this.year,
        synopsis = this.synopsis
    )
}