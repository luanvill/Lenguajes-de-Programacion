package com.example.proyectofinal3.Busqueda


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.proyectofinal3.R
import com.google.firebase.database.FirebaseDatabase
import java.util.jar.Manifest

class MascotaAdapter(val modContext : Context, val layoutId:Int, val mascotaList:List<Mascota>)
    : ArrayAdapter<Mascota>(modContext,layoutId,mascotaList) {
    val REQUEST_PHONE_CALL = 1
    val numero = 0
    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(modContext)
        val view: View = layoutInflater.inflate(layoutId, null)
        val REQUEST_PHONE_CALL = 1
        val nombre = view.findViewById<TextView>(R.id.idNombre)
        val sexo = view.findViewById<TextView>(R.id.idSexo)
        val descripcion = view.findViewById<TextView>(R.id.idDescripcion)
        val raza = view.findViewById<TextView>(R.id.idRaza)
        val telefono = view.findViewById<Button>(R.id.idLlamar)
        val adoptar = view.findViewById<Button>(R.id.idAdoptar)



        telefono.setOnClickListener({

           // makePhoneCall("0998680247")


        })


        //val deleteBtn = view.findViewById<TextView>(R.id.delete) borrar de la base de datos una vez lo encuentre en ella, lo haria con un boton

        val mascotas = mascotaList[pos]
        nombre.text = mascotas.nombre
        sexo.text = mascotas.sexo
        descripcion.text = mascotas.descripcion
        telefono.text = mascotas.telefono
        raza.text = mascotas.raza
        /*adoptar.setOnClickListener {
            adopta(mascotas)
        }*/
        return view


    }

   /* fun makePhoneCall(number: String): Boolean {
            val intent = Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse("tel:$number"))
            startActivity(modContext)
            return true

    }*/
}
//podria usarse para borrar la mascota de la lista y agregarla a la base de datos personal del usuario
/*
    private fun adopta(m:Mascota){
        val myDatabase = FirebaseDatabase.getInstance().getReference("Perro")
        myDatabase.child(m.toString()).removeValue()
        Toast.makeText(modContext,"Adoptado !", Toast.LENGTH_LONG).show()
    }
*/

