package example.login.retrofit

import example.login.retrofit.modelclass.New_register
import example.login.retrofit.modelclass.Sam
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface login_interface
{

@FormUrlEncoded
@POST("register/")
fun registration(
    @Field("username")username:String,
    @Field("password")password:String,
    @Field("password2")password2:String,
    @Field("email")email:String,
    @Field("first_name")first_name:String,
    @Field("last_name")last_name:String):Call<New_register>


@FormUrlEncoded
@POST("login/")
fun login(
    @Field("username")username: String,
    @Field("password")password: String
):Call<Sam>


}