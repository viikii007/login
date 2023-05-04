package example.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import example.login.navigation.navigation
import kotlinx.coroutines.delay

@SuppressLint("SuspiciousIndentation")
@Composable
fun AnimatedSplashScreen(
    mainActivity: MainActivity,
    navHostController: NavHostController,
)
{

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(key1 = true)
    {
        startAnimation = true
        delay(2000)
        navHostController.popBackStack()
        if (!utils.sharedhelper.getBoolean(mainActivity, utils.splashScreen))
        {
            navHostController.navigate(navigation.home.route)
        }
        else {
                navHostController.navigate(navigation.dashboard.route)
                 }
    }
    Splash(alpha = alphaAnim.value)


}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.baseline_login_24),
            contentDescription = "splashIcon",
            modifier = Modifier
                .alpha(alpha = alpha)
        )
    }
}