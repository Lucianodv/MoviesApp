package repository

import data.model.MovieList
import data.remote.MovieDataSource




class MovieRepositoryImplement(private val dataSource: MovieDataSource): MovieRepository {

    override suspend fun getUpComingMovies(): MovieList = dataSource.getUpComingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

}