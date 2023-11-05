# movie-night

Android app, to collecting your favorite movies/series

## Features

1. View list of movies, and details
2. Fetching cover photo
3. Write comment
4. Watch trailers (YouTube embeded view)
5. ~~Save to watch list~~
6. ~~Subscribe to the movie to receive latest updates~~
7. ~~Search for movies~~
8. ~~Share to other apps~~
9. ~~Connect to friends~~
10. ~~See number of friends watched the movie~~

Updated on 21/10/2023: descoped a few features so that the project could focus on one core feature which is to create notes of movie as quick as possible, and with the best user experience.

## Nuts and bolts

- Using Kotlin, ViewBinding
- Navigation Graph
- Room
- Flow
- Retrofit, ~~Coil~~ Glide Image Loading
- OpenAI for movie sypnosis

## Demo

Wireframe: detail, list view.

![Wireframe](Readme_images/wireframe_1.png)

## Evolution

| Detail View Day 1 | List View With DB | Detail View Day 2|
|--|--|--|
|![Day1](/Readme_images/Day_1b.png)|![Day3](/Readme_images/day3a_localdb.png)|![Day2b](/Readme_images/Detail_button.png)|

| Add Movie | V1 | V2 by fetching movie data from TMDB|
|--|--|--|
|![Day5](/Readme_images/Screenshot_20230916_125629.png)|![Day6a](/Readme_images/day6_improveUI_addMovie.png)|![Day6b](/Readme_images/day7_retrofit_tmdb.png)|