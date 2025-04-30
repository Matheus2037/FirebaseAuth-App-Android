package com.example.firebaseauth_app.FirebaseAuth

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GetAuthExceptionPTBR {
    private var errorMap: Map<String, String>? = null

    fun convertJsonToMapStringGson(context: Context){
        val json = context.assets.open("authErrorPTBR.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<Map<String, String>>() {}.type

        errorMap = Gson().fromJson(json, type)
    }

    fun getErrorMessagePTBR(errorCode: String): String {
        Log.e("Auth", "getErrorMessagePTBR: $errorCode")
        return errorMap?.get(errorCode) ?: "TESTE"
    }

}