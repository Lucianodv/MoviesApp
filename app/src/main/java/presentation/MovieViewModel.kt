package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import core.Resources
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import repository.MovieRepository

class MovieViewModel(private val repo: MovieRepository): ViewModel(){

   fun fetchMainScreenMovies() = liveData(Dispatchers.IO){

       emit(Resources.Loading())

       try {
           emit(Resources.succed(Triple(repo.getUpComingMovies(),repo.getPopularMovies(),repo.getTopRatedMovies())))
       }catch (e: Exception) {
       emit(Resources.failure(e))
       }
   }

}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory{
 override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}