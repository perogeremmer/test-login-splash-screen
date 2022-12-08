package com.example.belajarlogindansplashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.belajarlogindansplashscreen.api.RetrofitHelper
import com.example.belajarlogindansplashscreen.api.UserApi
import com.example.belajarlogindansplashscreen.api.data.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    lateinit var btnSignIn : Button
    lateinit var btnMoveToSignUp : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText


    val apiKey = ""
    val token = "Bearer $apiKey"

    val todoApi = RetrofitHelper.getInstance().create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn = findViewById(R.id.btn_sign_in)
        btnMoveToSignUp = findViewById(R.id.btn_move_to_sign_up)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        btnSignIn.setOnClickListener {
            signIn(etEmail.text.toString(), etPassword.text.toString())
        }

        btnMoveToSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val data = Users(email = email, password = password)
            val response = todoApi.signIn(token = token, apiKey = apiKey, data = data)

            val bodyResponse = if(response.code() == 200) {
                response.body()?.string()
            } else {
                response.errorBody()?.string().toString()
            }

            var failed = false
            val jsonResponse = JSONObject(bodyResponse)
            if(jsonResponse.keys().asSequence().toList().contains("error")) {
                failed = true
            }

            var msg = ""
            if (!failed) {
                var email = jsonResponse.getJSONObject("user").get("email").toString()
                msg = "Successfully login! Welcome back: $email"


                val sharedPreference =  getSharedPreferences(
                    "app_preference", Context.MODE_PRIVATE
                )

                var editor = sharedPreference.edit()
                editor.putString("email", email)
                editor.commit()

            } else {
                msg += jsonResponse.get("error_description").toString()
            }

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()

                if (!failed) {
                   goToHome();
                }
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}