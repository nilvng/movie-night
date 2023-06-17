package com.nillin.movienight.database

import com.nillin.movienight.R

data class Movie(
    val title: String,
    val creator: String,
    val actors: ArrayList<String>,
    val year: Int,
    val synopsis: String
)

val dummy_normalpeople = Movie(title = "Normal People",
    creator = "Sally Rooney",
    actors = arrayListOf("Daisy Edgar-Jones", "Paul Mescal"),
    year = 2020,
    synopsis = "Normal People is a British drama television series adapted by Sally Rooney from her 2018 novel of the same name. Produced by Element Pictures for BBC Three and Hulu, it follows the relationship between Marianne Sheridan (Daisy Edgar-Jones) and Connell Waldron (Paul Mescal), as they navigate adulthood from their final days in secondary school to their undergraduate years in Trinity College. The series premiered on 26 April 2020 on BBC Three in the UK and on 29 April 2020 on Hulu in the United States. The series received critical acclaim, with particular praise for its acting, directing, and writing. It was nominated for four Primetime Emmy Awards, including Outstanding Limited Series and acting nominations for Edgar-Jones and Mescal.")
