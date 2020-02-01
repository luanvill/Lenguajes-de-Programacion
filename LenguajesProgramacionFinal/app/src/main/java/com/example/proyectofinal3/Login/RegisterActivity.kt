package com.example.proyectofinal3.Login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.proyectofinal3.R
import com.example.proyectofinal3.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.properties.Delegates

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtNumber: EditText
    private lateinit var  progressBar: ProgressDialog
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    //global variables
    private var nombre by Delegates.notNull<String>()
    private var apellido by Delegates.notNull<String>()
    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private var numero by Delegates.notNull<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initialise()

    }


    private fun initialise() {
        txtName = findViewById(R.id.txtName)
        txtLastName = findViewById(R.id.txtLastName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        txtNumber = findViewById(R.id.txtNumber)
        progressBar = ProgressDialog(this)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

//referencia a la base de datos, en este caso es para los usuarios
        databaseReference = database.reference.child("Users")
    }

    private fun createNewAccount() {

        nombre = txtName.text.toString()
        apellido = txtLastName.text.toString()
        email = txtEmail.text.toString()
        password = txtPassword.text.toString()
        numero= txtNumber.text.toString()

        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido)
            && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {


            progressBar.setMessage("Usuario registrado...")
            progressBar.show()

//Crear usuario con contraseña
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {

                    val user:FirebaseUser = auth.currentUser!!
                    verifyEmail(user);
                    val currentUserDb = databaseReference.child(user.uid)

                    currentUserDb.child("Nombre").setValue(nombre)
                    currentUserDb.child("Apellido").setValue(apellido)
                    currentUserDb.child("Correo").setValue(email)
                    currentUserDb.child("Numero").setValue(numero)

                    updateUserInfoAndGoHome()

                }.addOnFailureListener{
                    // si el registro falla se mostrara este mensaje
                    Toast.makeText(this, "Error en la autenticación.",
                        Toast.LENGTH_SHORT).show()
                }

        } else {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    //llamamos el método de crear cuenta en la accion registrar
    fun register(view: View){
        createNewAccount()
    }

    private fun updateUserInfoAndGoHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        progressBar.hide()

    }

    private fun verifyEmail(user: FirebaseUser) {
        user.sendEmailVerification()
            .addOnCompleteListener(this) {
                    task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Email " + user.getEmail(),
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,
                        "Error al verificar el correo ",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    }


