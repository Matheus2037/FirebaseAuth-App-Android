package com.example.firebaseauth_app.FirebaseAuth

import android.util.Log
import com.example.firebaseauth_app.FirebaseAuth.GetAuthExceptionPTBR.getErrorMessagePTBR
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

object FirebaseAuthManager {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun createUser(email: String, password: String, onResult: (Boolean, FirebaseUser?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, auth.currentUser)
                } else {
                    Log.e("Auth", "createUser: ${task.exception?.message}")
                    onResult(false, null)
                }
            }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, FirebaseUser?, errorMessage: String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, auth.currentUser, null)
                } else {
                    val exception = task.exception
                    if (exception is FirebaseAuthException) {
                        val errorCode = exception.errorCode
                        Log.e("Auth", "loginUser: ${getErrorMessagePTBR(errorCode)}")
//                        val erroPTBR = getErrorMessagePTBR(errorCode)
//                        Log.e("Auth", "loginUser: $erroPTBR")
                        onResult(false, auth.currentUser, getErrorMessagePTBR(errorCode))
                    } else {
                        Log.e("Auth", "loginUser: Erro inesperado = ${exception?.message}")
                        onResult(false, null, null)
                    }
                }
            }
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    fun logout() = auth.signOut()

}