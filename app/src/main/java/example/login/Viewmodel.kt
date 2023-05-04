package example.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import example.login.retrofit.Repository
import example.login.retrofit.modelclass.Sam
import example.login.retrofit.modelclass.New_register
import kotlinx.coroutines.runBlocking
import retrofit2.Call

class Viewmodel_registration(application: Application): AndroidViewModel(application) {
    private var repository: Repository? = null

    init {
        repository = Repository()
    }

    fun registration(
        username: String,
        password: String,
        password2: String,
        email: String,
        first_name: String,
        last_name: String
    ): Call<New_register>? =
        runBlocking {
            repository?.registration(username, password,password2,email, first_name, last_name)
        }

    fun login(username: String,password: String):Call<Sam>?=
        runBlocking {
            repository?.login(username,password)
        }

}

