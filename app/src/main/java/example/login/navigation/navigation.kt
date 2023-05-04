package example.login.navigation

sealed class navigation(var route:String)
{
    object home:navigation("home")
    object splashscreen:navigation("splashscreen")
    object registration:navigation("registration")
    object login:navigation("login")
    object dashboard:navigation("dashboard")
}
