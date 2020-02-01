package com.example.proyectofinal3.Adoptar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.proyectofinal3.Busqueda.AdoptarAdapter
import com.example.proyectofinal3.Busqueda.Mascota
import com.example.proyectofinal3.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_mascota2.*

class AdoptarActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var mascotaList:MutableList<AdoptarMascota>
    lateinit var listview: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adoptar)


        mascotaList = mutableListOf()
        listview = findViewById(R.id.list2)
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
                        val m = e.getValue(AdoptarMascota::class.java)
                        mascotaList.add(m!!)
                    }
                    val adapter =
                        AdoptarAdapter(
                            this@AdoptarActivity,R.layout.item_mascota2,mascotaList                        )

                    listview.adapter = adapter
                }
            }




        })
    }
}

