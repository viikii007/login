package example.login

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import example.login.navigation.navigation
import example.login.retrofit.modelclass.Sam
import example.login.retrofit.modelclass.New_register
import retrofit2.Call
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException

fun sign_apicall(mainActivity: MainActivity,username:MutableState<String>,password:MutableState<String>,password2:MutableState<String>,mail:MutableState<String>,first_name:MutableState<String>,last_name:MutableState<String>,navHostController: NavHostController)
{
    var registration_viewmodel_obj =
        ViewModelProvider(mainActivity)[Viewmodel_registration::class.java]

    try {
        registration_viewmodel_obj.registration(
            username.value,
            password.value,
            password2.value,
            mail.value,
            first_name.value,
            last_name.value
        )?.enqueue(object :
            retrofit2.Callback<New_register> {
            override fun onResponse(call: Call<New_register>, response: Response<New_register>) {
                var registration_responce = response.body()

                try {
                    if (!response.isSuccessful) {
                        Toast.makeText(mainActivity, "Enter valid data", Toast.LENGTH_SHORT)
                            .show()
                        return
                    } else if (registration_responce!!.email.isNullOrEmpty() && registration_responce.username.isNullOrEmpty()) {
                        Toast.makeText(mainActivity, "Enter valid data", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mainActivity, "Register successfully", Toast.LENGTH_SHORT).show()
                        navHostController.navigate(navigation.login.route)
                    }

                } catch (e: SocketTimeoutException) {
                    e.printStackTrace()
                } catch (e: SocketException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<New_register>, t: Throwable) {
                try {
                    Toast.makeText(mainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()

                    println("error in registration${t.message}")
                    Log.d("", "")
                } catch (e: SocketTimeoutException) {
                    e.printStackTrace()
                } catch (e: SocketException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    } catch (e: SocketException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun login_apical(mainActivity: MainActivity, username: MutableState<String>, password: MutableState<String>,navHostController: NavHostController)
{
    var registration_viewmodel_obj = ViewModelProvider(mainActivity)[Viewmodel_registration::class.java]

    try {
        registration_viewmodel_obj.login(username.value,password.value)?.enqueue(object :
            retrofit2.Callback<Sam> {
            override fun onResponse(call: Call<Sam>, response: Response<Sam>)
            {
                var login_responce=response.body()

                try
                {
                    if (!response.isSuccessful)
                    {
                        Toast.makeText(mainActivity,"Enter valid data",Toast.LENGTH_LONG).show()
                        return
                    }

                  else if(login_responce!!.email.isNullOrEmpty() && login_responce.username.isNullOrEmpty())
                    {
                        Toast.makeText(mainActivity,"Enter valid data",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(mainActivity, "Login successfully", Toast.LENGTH_LONG).show()
                        utils.sharedhelper.putBoolean(
                            mainActivity,
                            utils.splashScreen,
                            true
                        )
                        utils.sharedhelper.putString(mainActivity,utils.username, login_responce.username)
                        utils.sharedhelper.putString(mainActivity,utils.email, login_responce.email)

                        navHostController.navigate(navigation.dashboard.route)
                    }
                }
                catch (e: SocketTimeoutException)
                {
                    e.printStackTrace()
                }
                catch (e: SocketException)
                {
                    e.printStackTrace()
                }
                catch (e: Exception)
                {
                    e.printStackTrace()
                }

            }
            override fun onFailure(call: Call<Sam>, t: Throwable)
            {
                try {
                    Toast.makeText(mainActivity,"Something went wrong",Toast.LENGTH_LONG).show()

                    println("error in registration${t.message}")
                } catch (e: SocketTimeoutException) {
                    e.printStackTrace()
                } catch (e: SocketException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
    catch (e: SocketException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun registration_screen(mainActivity: MainActivity, navHostController: NavHostController)
{

    var first_name= remember {
        mutableStateOf("")
    }

    var last_name= remember {
        mutableStateOf("")
    }

    var username= remember {
        mutableStateOf("")
    }

    var email_id= remember {
        mutableStateOf("")
    }

    var password= remember {
        mutableStateOf("")
    }
    var password2= remember {
        mutableStateOf("")
    }


    Scaffold(topBar = {
        TopAppBar(title = {
            Box(modifier =Modifier.fillMaxWidth() )
            {
                Text(text = "SignUp", modifier = Modifier.align(Alignment.Center))
            }
        }, backgroundColor = Color.White, elevation = 0.dp)
    }, content = {
        Column(modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(), verticalArrangement = Arrangement.Top)
        {
            // user Name
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {

                Text(text = "User Name *", color = Color.Black, fontSize = 14.sp)

                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = username.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { username.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(text = "Enter your name", color = Color.DarkGray, fontSize = 12.sp)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }
            Spacer(modifier = Modifier.padding(7.dp))

            // password
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Password *", color = Color.Black, fontSize = 14.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = password.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { password.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(
                            text = "Enter your password",
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }
            Spacer(modifier = Modifier.padding(7.dp))

            // password2
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Conform password *", color = Color.Black, fontSize = 14.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = password2.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { password2.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(
                            text = "Enter your Conform password",
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }
            Spacer(modifier = Modifier.padding(7.dp))

            // email ID
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {

                Text(text = "Email ID *", color = Color.Black, fontSize = 14.sp)


                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = email_id.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { email_id.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(
                            text = "Enter your email ID",
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }
            Spacer(modifier = Modifier.padding(7.dp))

            // first Name
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {

                Text(text = "First Name", color = Color.Black, fontSize = 14.sp)

                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = first_name.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { first_name.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(text = "Enter your first name", color = Color.DarkGray, fontSize = 12.sp)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }
            Spacer(modifier = Modifier.padding(7.dp))

            // last Name
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {

                Text(text = "Last Name", color = Color.Black, fontSize = 14.sp)

                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = last_name.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { last_name.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(text = "Enter your last name", color = Color.DarkGray, fontSize = 12.sp)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
            {
                Button(onClick = {

                    if (username.value.trim().isEmpty())
                    {

                        Toast
                            .makeText(mainActivity, "Enter user name", Toast.LENGTH_LONG)
                            .show()
                    }

                    else if ((email_id.value).isNullOrEmpty()) {
                        Toast
                            .makeText(mainActivity, "Enter valid mail id", Toast.LENGTH_LONG)
                            .show()
                    }

                    else if ((password.value).isNullOrEmpty()) {
                        Toast
                            .makeText(mainActivity, "password is mandatory", Toast.LENGTH_LONG)
                            .show()
                    } else if ((password2.value).isNullOrEmpty()) {
                        Toast
                            .makeText(mainActivity, "password is mandatory", Toast.LENGTH_LONG)
                            .show()

                    }else if (password.value!=password2.value)
                    {
                        Toast.makeText(mainActivity, "Enter password & conform password not matched", Toast.LENGTH_LONG).show()
                    }
                    else if (!isValidPassword(password.value))
                    {
                        Toast.makeText(mainActivity, "Enter valid password", Toast.LENGTH_LONG).show()
                    }
                    else if (!isValidPassword(password2.value))
                    {
                        Toast.makeText(mainActivity, "Enter valid password", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        sign_apicall(mainActivity,username,password,password2,email_id,first_name,last_name,navHostController)

                    }



                                 }, colors = ButtonDefaults.buttonColors(Color.Blue))
                {
                    Text(text = "Create Account", textAlign = TextAlign.Center, color = Color.White)
                }
            }


        }
    })


}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun login_screen(mainActivity: MainActivity, navHostController: NavHostController)
{
    var username= remember {
        mutableStateOf("")
    }

    var password= remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Box(modifier =Modifier.fillMaxWidth() )
            {
                Text(text = "Login", modifier = Modifier.align(Alignment.Center))
            }
        }, backgroundColor = Color.White, elevation = 0.dp)
    }, content = {
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight(), verticalArrangement = Arrangement.Top)
        {
            // user Name
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {

                Text(text = "User Name *", color = Color.Black, fontSize = 14.sp)

                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = username.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { username.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, shape = RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(text = "Enter your user name", color = Color.DarkGray, fontSize = 12.sp)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }
            Spacer(modifier = Modifier.padding(7.dp))
            // password
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Password *", color = Color.Black, fontSize = 14.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    value = password.value,
                    textStyle = TextStyle(color = Color.Black, fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { password.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    placeholder = {
                        Text(
                            text = "Enter your password",
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }
                    ))
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
            {
                Button(onClick = {
                    if (username.value.trim().isEmpty())
                    {
                        Toast.makeText(mainActivity, "Enter user name", Toast.LENGTH_LONG).show()
                    }
                    else if ((password.value).isNullOrEmpty())
                    {
                        Toast
                            .makeText(mainActivity, "Password is mandatory", Toast.LENGTH_LONG)
                            .show()
                    }
                  else {
                        login_apical(mainActivity,username,password,navHostController)
                    } }, colors = ButtonDefaults.buttonColors(Color.Blue))
                {
                    Text(text = "Login", textAlign = TextAlign.Center, color = Color.White)
                }
            }

        }
    })

}


fun isValidPassword(password: String): Boolean
{
    if (password.length < 8) return false
    if (password.filter { it.isDigit() }.firstOrNull() == null) return false
    if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
    if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
    if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

    return true
}
