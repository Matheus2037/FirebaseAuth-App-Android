package com.example.firebaseauth_app.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun RegisterScreen(
    navController: NavController,
    onRegisterSuccess: () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.size(width = 300.dp, height = 400.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                Text(text = "Registro", fontSize = 24.sp, fontWeight = FontWeight.Bold)


                OutlinedTextField(
                    modifier = Modifier.padding(top = 16.dp),
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                OutlinedTextField(
                    value = passwordConfirmation,
                    onValueChange = { passwordConfirmation = it },
                    label = { Text("Confirm Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                error?.let {
                    Text(text = it, color = Color.Red)
                }

                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick ={
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if(password == passwordConfirmation) {
                                if (task.isSuccessful) {
                                    onRegisterSuccess()
                                } else {
                                    error = task.exception?.message
                                }
                            }else{
                                error = task.exception?.message
                            }
                        }
                }){
                    Text("Cadastrar")
                }

                TextButton(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = onRegisterSuccess) {
                    Text("Voltar")
                }
            }
        }
    }



//    Column(
//        Modifier.padding(16.dp)
//    ) {
//        Text(text = "Registro", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//
//        Spacer(Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            singleLine = true,
//            visualTransformation = PasswordVisualTransformation()
//        )
//
//        OutlinedTextField(
//            value = passwordConfirmation,
//            onValueChange = { passwordConfirmation = it },
//            label = { Text("Confirm Password") },
//            singleLine = true,
//            visualTransformation = PasswordVisualTransformation()
//        )
//
//        error?.let {
//            Text(text = it, color = Color.Red)
//        }
//
//        Button(onClick ={
//            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if(password == passwordConfirmation) {
//                        if (task.isSuccessful) {
//                            onRegisterSuccess()
//                        } else {
//                            error = task.exception?.message
//                        }
//                    }else{
//                        error = task.exception?.message
//                    }
//                }
//        }) {
//            Text("Cadastrar")
//        }
//
//        TextButton(onClick = onRegisterSuccess) {
//            Text("Criar conta")
//        }
//    }
}