package com.example.firebaseauth_app.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.firebaseauth_app.FirebaseAuth.FirebaseAuthManager
import com.example.firebaseauth_app.FirebaseAuth.GetAuthExceptionPTBR


@Composable
fun LoginScreen(
    navController: NavController,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.size(width = 300.dp, height = 400.dp)
        ){
            LoginElements(navController = navController,
                onLoginSuccess = {
                    navController.navigate("home")
                },
                onNavigateToRegister = {
                    navController.navigate("registro")
                })
        }
    }
}

@Composable
fun LoginElements(
    navController: NavController,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        GetAuthExceptionPTBR.convertJsonToMapStringGson(context)
    }

    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            modifier = Modifier.padding(top = 16.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        error?.let {
            Text(text = it, color = Color.Red)
        }

        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                if (email.isNotBlank() and password.isNotBlank()) {
                    FirebaseAuthManager.loginUser(email, password, { success, user, errorMessage ->
                        if (success) {
                            onLoginSuccess()
                        }
                        else{
                            error = errorMessage
                        }
                    })
                }else if (email.isNotBlank() and password.isBlank()){
                    error = "Preencha a senha"
                }else if (email.isBlank() and password.isNotBlank()){
                    error = "Preencha o e-mail"
                }
                else{
                    error = "Preencha todos os campos"
                }
            }
        ) { Text("Entrar") }

            TextButton(modifier = Modifier.padding(top = 8.dp),
                onClick = onNavigateToRegister) {
                Text("Criar conta")
            }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginCard()
//}