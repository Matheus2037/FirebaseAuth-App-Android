package com.example.firebaseauth_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth_app.Screen.LoginScreen
import com.example.firebaseauth_app.ui.theme.FireBaseAuthAppTheme
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebaseauth_app.Screen.HomeScreen
import com.example.firebaseauth_app.Screen.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FireBaseAuthAppTheme {
                App()
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable(
                route = "login",
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                LoginScreen(navController = navController,
                    onLoginSuccess = {
                        navController.navigate("home")
                    },
                    onNavigateToRegister = {
                        navController.navigate("registro")
                    })
            }

            composable(
                route = "registro",
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                RegisterScreen(
                    navController = navController,
                    onRegisterSuccess = {
                        navController.navigate("login")
                    }
                )
            }

            composable(
                route = "home",
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                HomeScreen(navController)
            }
        }
    }
}