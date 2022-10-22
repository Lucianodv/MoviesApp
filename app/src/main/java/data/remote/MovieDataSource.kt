package data.remote

import com.example.moviesapp.application.AppConstants
import data.model.MovieList
import repository.WebService

class MovieDataSource(private val webService: WebService) {

    //funcion que trae las peliculas proximas a lanzar de la api y retorna la lista que creamos en el model
  suspend  fun getUpComingMovies(): MovieList{
        return webService.getUpComingMovies(AppConstants.API_KEY)
    }

    //funcion que trae las peliculas mejor votadas de la api y retorna la lista que creamos en el model
   suspend fun getTopRatedMovies(): MovieList{
        return webService.getTopRatedMovies(AppConstants.API_KEY)
    }


    //funcion que trae las peliculas populares de la api y retorna la lista que creamos en el model
   suspend fun getPopularMovies(): MovieList{
        return webService.getPopularMovies(AppConstants.API_KEY)
    }


}