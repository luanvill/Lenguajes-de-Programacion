package com.espol.lenguajesprogramacionfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cargarElementosGraficos()
        FirebaseAuth.getInstance().signInAnonymously()
    }

    fun cargarElementosGraficos(){
        val perrosPerdidosBoton = findViewById(R.id.perrosPerdidos) as Button
        val adoptaPerroBoton = findViewById(R.id.adopciones) as Button
        val encontrePerroBoton: Button = findViewById(R.id.encontrePerro)
        perrosPerdidosBoton.setOnClickListener {
            val intentPerrosPerdidos: Intent? = Intent(this,PerrosPerdidosActivity::class.java).apply{
                //Algo de mensaje
            }
            Log.i("MAIN","Pasando a actividad Perros Perdidos")
            startActivity(intentPerrosPerdidos)

        };
        adoptaPerroBoton.setOnClickListener{
            //Lanzar Actividad
            val intentPerrosAdoptar: Intent? = Intent(this,PerrosAdoptarActivity::class.java).apply{
                //Algo de mensaje
            }
            Log.i("MAIN","Pasando a actividad Perros Adoptar")
            startActivity(intentPerrosAdoptar)
        }
        encontrePerroBoton.setOnClickListener{
            //Lanzar Actividad
            val intentPerrosFormulario: Intent? = Intent(this,PerrosFormularioActivity::class.java).apply{
                //Algo de mensaje
            }
            Log.i("MAIN","Pasando a actividad Perros Formulario")
            startActivity(intentPerrosFormulario)
        }

    }
}
