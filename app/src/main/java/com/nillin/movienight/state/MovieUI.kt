package com.nillin.movienight.state

data class MovieUI(
    val id: Int = 0,
    val title: String,
    val cover: String,
    val creator: String,
    val actors: List<String>,
    val year: Int,
    val synopsis: String
)
val dummy_silo = MovieUI(
    id = 1,
    title = "Silo",
    cover = "https://m.media-amazon.com/images/M/MV5BNTk3MGJkZGItNzRjYy00MDhiLWExMjUtOWU2Njc3YWRmOWE3XkEyXkFqcGdeQXVyMjkwOTAyMDU@._V1_.jpg",
    creator = "Graham Yost",
    actors = arrayListOf("Rebecca Ferguson", "Rashida Jones", "David Oyelowo"),
    year = 2023,
    synopsis = "an American science fiction dystopian drama television series created by Graham Yost based on the Wool series of novels by author Hugh Howey. Set in a dystopian future where a community exists in a giant underground silo comprising 144 levels"
)
val dummy_normalpeople = MovieUI(
    id = 2,
    title = "Normal People",
    cover = "https://m.media-amazon.com/images/M/MV5BNzMzYmRiNGEtMDg5OC00OGZmLWFmNDktYzRlZTFkZmZiMjAzXkEyXkFqcGdeQXVyMTE2OTE2MzE1._V1_.jpg",
    creator = "Sally Rooney",
    actors = arrayListOf("Daisy Edgar-Jones", "Paul Mescal"),
    year = 2020,
    synopsis = "Normal People is a British drama television series adapted by Sally Rooney from her 2018 novel of the same name. Produced by Element Pictures for BBC Three and Hulu, it follows the relationship between Marianne Sheridan (Daisy Edgar-Jones) and Connell Waldron (Paul Mescal), as they navigate adulthood from their final days in secondary school to their undergraduate years in Trinity College. The series premiered on 26 April 2020 on BBC Three in the UK and on 29 April 2020 on Hulu in the United States. The series received critical acclaim, with particular praise for its acting, directing, and writing. It was nominated for four Primetime Emmy Awards, including Outstanding Limited Series and acting nominations for Edgar-Jones and Mescal.")
