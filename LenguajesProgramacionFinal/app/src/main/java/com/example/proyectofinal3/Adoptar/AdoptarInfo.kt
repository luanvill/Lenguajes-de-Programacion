package com.example.proyectofinal3.Busqueda

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal3.Adoptar.AdoptarMascota
import com.example.proyectofinal3.R
import com.google.firebase.database.*

class AdoptarInfo: AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var mascotaList:MutableList<Mascota>
    lateinit var listview:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adoptar)

        mascotaList = mutableListOf()
        listview = findViewById(R.id.list2)
        ref = FirebaseDatabase.getInstance().getReference("Perro")

        ref.addValueEventListener(object :ValueEventListener{



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
                            this@AdoptarInfo,
                            R.layout.item_mascota,
                            mascotaList
                        )
                    listview.adapter = adapter


                }
            }

        })
    }


}