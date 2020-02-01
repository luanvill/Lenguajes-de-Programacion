package com.example.proyectofinal3.Busqueda


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyectofinal3.Adoptar.AdoptarMascota
import com.example.proyectofinal3.R
import com.google.firebase.database.FirebaseDatabase


class AdoptarAdapter(val mCtx : Context , val layoutId:Int , val adoptarLista:List<AdoptarMascota>)
    :ArrayAdapter<AdoptarMascota>(mCtx,layoutId,adoptarLista){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutId, null)

        val nombre = view.findViewById<TextView>(R.id.idNombre)
        val sexo = view.findViewById<TextView>(R.id.idSexo)
        val descripcion = view.findViewById<TextView>(R.id.idDescripcion)
        val raza = view.findViewById<TextView>(R.id.idRaza)

       // val adoptar = view.findViewById<Button>(R.id.idAdoptar)

        val adoptar = view.findViewById<TextView>(R.id.idAdoptar)


        val mascotas = adoptarLista[position]


        nombre.text = mascotas.nombre
        sexo.text = mascotas.sexo
        descripcion.text = mascotas.descripcion
        raza.text = mascotas.raza



        adoptar.setOnClickListener {
            upDateInfo(mascotas)
        }

        return view
    }



    private fun upDateInfo(mascota:AdoptarMascota){
        val myDatabase = FirebaseDatabase.getInstance().getReference("Perro")
        myDatabase.child(mascota.uid).removeValue()

        Toast.makeText(mCtx,"Modificacion. !",Toast.LENGTH_LONG).show()
    }



}
