package com.example.belajarlogindansplashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    lateinit var txtMessageLoading : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMessageLoading = findViewById(R.id.txt_message_loading)
        txtMessageLoading.text = "Initializing your app for the first time \uD83D\uDE09"

        Timer().schedule(3000) {
            goToSignInPage()
        }
    }

    private fun goToSignInPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}