package io.github.sadeghit.loginregister.navigation


sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Register : Screens("register")
    object PostList : Screens("postList")
    object SplashScreen : Screens("splashscreen")
}


