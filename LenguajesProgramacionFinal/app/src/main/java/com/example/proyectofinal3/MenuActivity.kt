package com.example.proyectofinal3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinal3.Adoptar.AdoptarActivity
import com.example.proyectofinal3.Busqueda.BusquedaActivity
import com.example.proyectofinal3.Registro.InformeActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        btnAdoptar.setOnClickListener{
            val intento2 = Intent(this,
                AdoptarActivity::class.java)
            startActivity(intento2)
        }
        btnBuscarM.setOnClickListener{
            val intento3 = Intent(this,
                BusquedaActivity::class.java)
            startActivity(intento3)
        }
        btnInforme.setOnClickListener{
            val intento4 = Intent(this,
                InformeActivity::class.java)
            startActivity(intento4)
        }
    }
}
