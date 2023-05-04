package example.login

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import example.login.navigation.navigation

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun home(mainActivity: MainActivity,navHostController: NavHostController)
{
    Scaffold(topBar = {
        TopAppBar(title = {
            Box(modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Login / Register",fontWeight = FontWeight.W700, fontSize = 20.sp, modifier = Modifier.align(Alignment.Center))

            }
        }, backgroundColor = Color.White)
    }, content = {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically)
        {
            Button(onClick = { navHostController.navigate(navigation.registration.route) }, colors = ButtonDefaults.buttonColors(
                Color.Blue))
            {
                Text(text = "Registration", textAlign = TextAlign.Center, color = Color.White)

            }
            
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = { navHostController.navigate(navigation.login.route) }, colors = ButtonDefaults.buttonColors(
                Color.Blue))
            {
                Text(text = "Login", textAlign = TextAlign.Center, color = Color.White)

            }
        }
    })

    BackHandler(onBack = {
        mainActivity.finishAffinity()
        mainActivity.finish()
    })
}
