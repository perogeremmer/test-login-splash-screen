package com.example.belajarlogindansplashscreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var btnSignOut : Button
    lateinit var txtLabel : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnSignOut = findViewById(R.id.btn_sign_out)
        txtLabel = findViewById(R.id.txt_label_home)

        val sharedPreference =  getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        var name = sharedPreference.getString("email", "[No email found]").toString()
        txtLabel.text = name


        btnSignOut.setOnClickListener {
            var editor = sharedPreference.edit()
            editor.clear()
            editor.remove("email")
            editor.commit()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }

    }
}