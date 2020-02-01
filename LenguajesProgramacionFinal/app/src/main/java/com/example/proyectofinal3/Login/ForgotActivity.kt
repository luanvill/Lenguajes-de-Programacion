package com.example.proyectofinal3.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectofinal3.R
import com.example.proyectofinal3.MainActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotActivity : AppCompatActivity() {
    private var email: EditText? = null
    private var btnSend: Button? = null
    //Firebase references
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        initialise()
    }
    private fun initialise() {
        email = findViewById<View>(R.id.etEmail) as EditText
        btnSend = findViewById<View>(R.id.btnSend) as Button
        mAuth = FirebaseAuth.getInstance()
        btnSend!!.setOnClickListener { sendPasswordResetEmail() }
    }
    private fun sendPasswordResetEmail() {
        val email = email?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            mAuth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Email Enviado", Toast.LENGTH_SHORT).show()
                        accion()
                    } else {
                        Toast.makeText(this, "No se encontró el usuario con este correo", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Se agregó un correo", Toast.LENGTH_SHORT).show()
        }
    }
    private fun accion() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

}
