package repository

import com.example.moviesapp.application.AppConstants
import com.google.gson.GsonBuilder
import data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {


    /*le indico en el get donde va ir a buscar esta info, y entre parentesis la base url que guarde en el object
    en query hay que pasarle la api key recordar poner el mismo nombre en este caso api_key
     */

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(@Query ("api_key") apiKey: String) : MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query ("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query ("api_key") apiKey: String): MovieList

}
// genero la peticion al servidor, paso la base url por retrofit y uso el convertidor GSON

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}