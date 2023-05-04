package example.login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.login.*

@Composable
fun setupnav(navHostController: NavHostController, mainActivity: MainActivity) {

    NavHost(navController = navHostController, startDestination = navigation.splashscreen.route)
    {

        composable(navigation.splashscreen.route)
        {
            AnimatedSplashScreen(mainActivity = mainActivity, navHostController = navHostController)
        }

        composable(navigation.home.route)
        {
            home(mainActivity = mainActivity, navHostController = navHostController)
        }

        composable(navigation.registration.route)
        {
            registration_screen(mainActivity = mainActivity,navHostController)
        }

        composable(navigation.login.route)
        {
            login_screen(mainActivity,navHostController)
        }

        composable(navigation.dashboard.route)
        {
            dashboard_screen(mainActivity = mainActivity, navHostController =navHostController )
        }

    }
}