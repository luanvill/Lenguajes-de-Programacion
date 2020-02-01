package com.example.proyectofinal3.Busqueda

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.core.app.ActivityCompat
import com.example.proyectofinal3.R
import com.google.firebase.database.*


class BusquedaActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var mascotaList:MutableList<Mascota>
    lateinit var listview: ListView
    //val telefono = findViewById<Button>(R.id.idLlamar)
    //val REQUEST_PHONE_CALL=1

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda)

       /* telefono.setOnClickListener{
            val numero=telefono.text.toString()
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero))
            if  (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=
                    PackageManager.PERMISSION_GRANTED)
            {
                return@setOnClickListener

            }
            startActivity(intent)

        }*/


        mascotaList = mutableListOf()
        listview = findViewById(R.id.list1)
        ref = FirebaseDatabase.getInstance().getReference("Perro")

        ref.addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    mascotaList.clear()
                    for (e in p0.children){
                        val m = e.getValue(Mascota::class.java)
                        mascotaList.add(m!!)
                    }
                    val adapter =
                        MascotaAdapter(
                            this@BusquedaActivity,
                            R.layout.item_mascota,
                            mascotaList
                        )
                    listview.adapter = adapter
                }
            }




        })
    }


    }