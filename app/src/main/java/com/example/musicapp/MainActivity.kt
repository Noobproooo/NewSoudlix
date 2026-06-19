package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.edittextEmail)
        val password = findViewById<EditText>(R.id.edittextPassword)
        val login = findViewById<Button>(R.id.buttonLogin)

        val db = DatabaseHelper(this)

        login.setOnClickListener {

            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (db.checkLogin(userEmail, userPassword)) {

                Toast.makeText(
                    this,
                    "Đăng nhập thành công",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
                    )
                )

            } else {

                Toast.makeText(
                    this,
                    "Sai tài khoản hoặc mật khẩu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}