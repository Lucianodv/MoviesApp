package repository

import data.model.MovieList

interface MovieRepository {

   suspend fun getUpComingMovies(): MovieList
   suspend fun getTopRatedMovies(): MovieList
   suspend fun getPopularMovies(): MovieList


}