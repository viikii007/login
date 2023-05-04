package example.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import example.login.navigation.navigation

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun dashboard_screen(mainActivity: MainActivity,navHostController: NavHostController)
{
    Scaffold(topBar = {
        TopAppBar(title = {
            Box(modifier = Modifier.fillMaxWidth())
            {
                Text(text = "DashBoard",fontWeight = FontWeight.W700, fontSize = 20.sp, modifier = Modifier.align(Alignment.Center))
            }
        }, backgroundColor = Color.White)
    }, content = {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "User Name:", fontWeight = FontWeight.W700, fontSize = 20.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = utils.sharedhelper.getString(mainActivity,utils.username).toString())

            }
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Email ID:",fontWeight = FontWeight.W700, fontSize = 20.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = utils.sharedhelper.getString(mainActivity,utils.email).toString())
            }

            Button(onClick = {
                Toast.makeText(mainActivity,"Logout successfully", Toast.LENGTH_SHORT).show()
                utils.sharedhelper.putBoolean(
                    mainActivity,
                    utils.splashScreen,
                    false
                )
                navHostController.navigate(navigation.home.route)

            }, colors = ButtonDefaults.buttonColors(Color.Blue))
            {
                Text(text = "Logout", color = Color.White)
            }
        }
    })


    BackHandler(onBack = {
        mainActivity.finishAffinity()
        mainActivity.finish()
    })
}