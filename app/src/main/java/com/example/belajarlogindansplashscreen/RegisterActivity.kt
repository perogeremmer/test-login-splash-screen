package com.example.belajarlogindansplashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.belajarlogindansplashscreen.api.RetrofitHelper
import com.example.belajarlogindansplashscreen.api.UserApi
import com.example.belajarlogindansplashscreen.api.data.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    lateinit var btnSignUp : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText


    val apiKey = ""
    val token = "Bearer $apiKey"

    val todoApi = RetrofitHelper.getInstance().create(UserApi::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnSignUp = findViewById(R.id.btn_sign_up)
        etEmail = findViewById(R.id.et_regis_email)
        etPassword = findViewById(R.id.et_regis_password)

        btnSignUp.setOnClickListener {
            signUp(etEmail.text.toString(), etPassword.text.toString())
        }

    }


    private fun signUp(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {

            val data = Users(email = email, password = password)
            val response = todoApi.signUp(token = token, apiKey = apiKey, data = data)

            Toast.makeText(
                applicationContext,
                "Berhasil daftar!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}