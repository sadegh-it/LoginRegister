package io.github.sadeghit.loginregister.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.sadeghit.loginregister.ui.screen.LoginScreen
import io.github.sadeghit.loginregister.ui.screen.PostsScreen
import io.github.sadeghit.loginregister.ui.screen.RegisterScreen
import io.github.sadeghit.loginregister.ui.screen.SplashScreen
import io.github.sadeghit.loginregister.viewModel.LoginViewModel
import io.github.sadeghit.loginregister.viewModel.PostViewModel


@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            SplashScreen(navController = navController, viewModel = loginViewModel)
        }

        composable(Screens.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(navController = navController, viewModel = loginViewModel)
        }

        composable(Screens.Register.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            RegisterScreen(navController = navController, viewModel = loginViewModel)
        }

        composable(Screens.PostList.route) {
            val postViewModel: PostViewModel = hiltViewModel()
            val loginViewModel: LoginViewModel = hiltViewModel()

            PostsScreen(
                viewModel = postViewModel,
                loginViewModel = loginViewModel,
                navController = navController
            )
        }
    }
}
