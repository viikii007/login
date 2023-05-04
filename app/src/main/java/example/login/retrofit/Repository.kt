package example.login.retrofit

import example.login.retrofit.modelclass.New_register
import example.login.retrofit.modelclass.Sam
import retrofit2.Call

class Repository
{

    private var apiInterface: login_interface? = null

    init {
        apiInterface = Apiservice.create()
    }

    fun registration(username:String,password:String,password2:String,email:String,first_name:String,last_name:String): Call<New_register>?
    {
        return apiInterface?.registration(username,password,password2,email,first_name,last_name)
    }

    fun login(username: String,password: String):Call<Sam>?
    {
        return apiInterface?.login(username,password)
    }

}
