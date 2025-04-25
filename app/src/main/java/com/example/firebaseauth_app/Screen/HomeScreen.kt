package com.example.firebaseauth_app.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomeScreen(navController: NavController){
    Column(Modifier.padding(16.dp)){

        Text(text = "Login realizado com sucesso")

        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate("login"){
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text(text = "Voltar")
        }
    }
}