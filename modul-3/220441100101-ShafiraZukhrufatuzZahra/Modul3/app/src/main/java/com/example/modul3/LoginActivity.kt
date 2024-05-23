package com.example.modul3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.modul3.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.username_edit)
        passwordInput = findViewById(R.id.password_edit)

        val loginBtn = findViewById<MaterialButton>(R.id.login_button)

        loginBtn.setOnClickListener {

            if (validateInput()) {

                val intent = Intent(this, MainActivity::class.java)

                intent.putExtra("name", usernameInput.text.toString())

                startActivity(intent)
            } else {

                Toast.makeText(this, "Tolong masukkan inputan dengan benar!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput() : Boolean{
        // Set error ke 0
        var error = 0

        if(usernameInput.text.toString().isEmpty()) {

            error++

            usernameInput.error = "Tolong masukkan username kamu!"
        }

        if (passwordInput.text.toString().isEmpty()) {

            error++

            passwordInput.error = "Tolong masukkan password kamu!"
        }

        return error == 0
    }
}