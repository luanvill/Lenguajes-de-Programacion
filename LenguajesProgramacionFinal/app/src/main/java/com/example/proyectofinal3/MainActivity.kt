package com.example.proyectofinal3


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils

import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.proyectofinal3.Login.ForgotActivity
import com.example.proyectofinal3.Login.RegisterActivity

import com.google.firebase.auth.FirebaseAuth

import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(){


    private val TAG = "LoginActivity"
    //global variables
    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var mProgressBar: ProgressDialog
    val REQUEST_PHONE_CALL=1


    //Creamos nuestra variable de autenticación firebase
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialise()
        /*idLlamar.setOnClickListener{
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
            }else{
                comenzarLlamada()
            }
        }*/
    }


    /*Creamos un método para inicializar nuestros elementos del diseño y la autenticación de firebase*/
    private fun initialise() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        mProgressBar = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
    }

//Ahora vamos a Iniciar sesión con firebase es muy sencillo

    private fun loginUser() {
        //Obtenemos usuario y contraseña
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        //Verificamos que los campos no este vacios
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            //Mostramos el progressdialog
            mProgressBar.setMessage("Autentificando Datos...")
            mProgressBar.show()

            //Iniciamos sesión con el método signIn y enviamos usuario y contraseña
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {

                    //Verificamos que la tarea se ejecutó correctamente
                        task ->
                    if (task.isSuccessful) {
                        // Si se inició correctamente la sesión vamos a la vista Home de la aplicación
                        goHome() // Creamos nuestro método en la parte de abajo
                    } else {
                        // sino le avisamos el usuairo que orcurrio un problema
                        Toast.makeText(this, "Fallo en la autentificación.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Ingresas detalles", Toast.LENGTH_SHORT).show()
        }
    }


    private fun goHome() {
//Ocultamos el progress
        mProgressBar.hide()
//Nos vamos a Home
        val intent = Intent(this, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

/* Tenemos que crear nuestros métodos con el mismo nombre que le agregamos a nuestros botones en el atributo onclick y forzosamente tenemos que recibir un parámetro view para que sea reconocido como click de nuestro button ya que en view podemos modificar los atributos*/

    /*Primero creamos nuestro evento login dentro de este llamamos nuestro método loginUser al dar click en el botón se iniciara sesión */
    fun login(view: View) {
        loginUser()
    }

/*Si se olvido de la contraseña lo enviaremos en la siguiente actividad nos marcara error porque necesitamos crear la actividad*/

    fun forgotPassword(view: View) {
        startActivity(Intent(this,
            ForgotActivity::class.java))
    }

/*Si quiere registrarse lo enviaremos en la siguiente actividad nos marcara error porque necesitamos crear la actividad*/

    fun register(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
/*
    @SuppressLint("MissingPermission")
    private fun comenzarLlamada(){
        val callIntent= Intent(Intent.ACTION_CALL)
        callIntent.data= Uri.parse(idLlamar.toString())
        startActivity(callIntent)
    }
    override fun onRequestPermissionsResult(        requestCode: Int,        permissions: Array<out String>,        grantResults: IntArray){

        if (requestCode==REQUEST_PHONE_CALL)comenzarLlamada()
    }
*/
}

