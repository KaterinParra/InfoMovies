package com.example.infomovies

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.infomovies.databinding.ActivityMainBinding
import com.example.infomovies.interfaces.FailureCallback
import com.example.infomovies.interfaces.SuccessCallBack
import com.example.infomovies.models.Client
import com.example.infomovies.models.Movie
import com.example.infomovies.models.MoviesDatabase
import com.example.infomovies.models.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelMovie : ViewModel(){
    var movies : MutableLiveData<ArrayList<Movie>> = MutableLiveData<ArrayList<Movie>>()
    lateinit var binding : ActivityMainBinding
    private var client = Client()
    private var db : MoviesDatabase? = null


    fun getMovies(context: Context){

        CoroutineScope(Dispatchers.IO).launch {
            client.getMovies(object:SuccessCallBack{
                override fun onSuccess(response: Response) {
                    if (response.status_code == 0) {
                        movies.value = response.results
                        insertMovie(context, response.results)
                    } else {
                        binding.errorMessage.visibility = View.VISIBLE
                        binding.btnErrorMessage.visibility = View.VISIBLE
                        //onFailureAction(context)
                    }

                }
            }, object:FailureCallback {
                override fun onFailure() {
                    movies.value = ArrayList<Movie>()
                    /*Toast.makeText(
                        context,
                        "There was a problem loading the data. Please, try again. Trying to show local data... ",
                        Toast.LENGTH_LONG)
                        .show()*/
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.btnErrorMessage.visibility = View.VISIBLE
                    //onFailureAction(context)
                }
            })
        }

    }

    private fun onFailureAction (context:Context) {
        Toast.makeText(
            context,
            "There was a problem loading the data. Trying to load it from local storage...",
            Toast.LENGTH_LONG)
            .show()
        getMoviesFromLocal(context)
    }

    fun getMoviesFromLocal(context: Context){
        instantiateDatabase(context)

        CoroutineScope(Dispatchers.IO).launch {
            val array = ArrayList<Movie>()
            array.addAll(db!!.moviesDAO().getAll())

            if (array.size > 0) {
                movies.postValue(array)
            } else {
                Toast.makeText(
                    context,
                    "There aren't movies in the local storage. ",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun insertMovie (context: Context, movies: ArrayList<Movie>) {
        instantiateDatabase(context)

        CoroutineScope(Dispatchers.IO).launch {
            movies.iterator().forEach {
                try {
                    db!!.moviesDAO().insertMovie(it)
                } catch (ex : SQLiteConstraintException) {
                    Log.e("Constraint", "The id already exists in database. $ex")
                }

            }
        }


    }

    private fun instantiateDatabase(context:Context) {
        if (db == null) {
            db = Room.databaseBuilder(context.applicationContext,
                MoviesDatabase::class.java, "MoviesDatabase")
                .build()
        }
    }
}