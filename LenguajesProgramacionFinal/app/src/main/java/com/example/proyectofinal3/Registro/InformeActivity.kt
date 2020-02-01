package com.example.proyectofinal3.Registro

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.proyectofinal3.MenuActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_informe.*
import java.util.*
import com.example.proyectofinal3.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_menu.*
import java.io.IOException

class InformeActivity : AppCompatActivity() {

    private val PICK_IMAGE=1000

    private lateinit var mProgressBar: ProgressDialog


    //lateinit var storage: FirebaseStorage
    private lateinit var filePath:Uri
    internal var storage:FirebaseStorage?=null
    internal var storageReference:StorageReference?=null

    val database = FirebaseDatabase.getInstance()


    val myRef = database.getReference("Perro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe)
setContentView(R.layout.activity_informe)
        storage= FirebaseStorage.getInstance()
        storageReference=storage!!.reference

//boton para seleccionar la foto
        seleccionar_foto.setOnClickListener {
             val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }


        confirmarFormulario.setOnClickListener {
            realizarRegistro()


             //subir foto al storage
                    //TO DO
            //cambiar al menu nuevamente
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        cancelarFormulario.setOnClickListener {
            super.onBackPressed()
        }


    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data


            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            val bitmapDrawable = BitmapDrawable(bitmap)

            seleccionar_foto.setBackgroundDrawable(bitmapDrawable)

            seleccionar_foto.setText("")

        }

    }

    private fun realizarRegistro() {
        val nombre = nombre_r.text.toString()
        val raza = raza_r.text.toString()
        val telefono =telefono_r.text.toString()
        val sexo = sexo_r.text.toString()
        val descripcion = descripcion_r.text.toString()

        if (nombre.isEmpty() || raza.isEmpty() || telefono.isEmpty() || sexo.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }

        Toast.makeText(this, "Agregando perrito perdido", Toast.LENGTH_SHORT).show()

        val perroId = UUID.randomUUID().toString()//id generada al azar

        val myr = myRef.child(perroId)
        val n1 = myr.child("nombre")
        val n2 = myr.child("raza")
        val n3 = myr.child("telefono")
        val n4 = myr.child("sexo")
        val n5 = myr.child("descripcion")
        //agregar un caracter para la direccion de la foto del firebase
        n1.setValue(nombre)
        n2.setValue(raza)
        n3.setValue(telefono)
        n4.setValue(sexo)
        n5.setValue(descripcion)

    }
/*
    private fun upPhotoToStorage(){
        if (filePath!=null){
           /* val progresssDialog=ProgressDialog(this)
            progresssDialog.setTitle("Subiendo...")
            progresssDialog.show()
            */
            val imageRef=storageReference!!.child("images/"+UUID.randomUUID().toString())//ID para subir
            imageRef.putFile(filePath!!)
            /*    .addOnSuccessListener {
                    progresssDialog.dismiss()
                    Toast.makeText(applicationContext,"Archivo subido",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    progresssDialog.dismiss()
                    Toast.makeText(applicationContext,"Problemas al subir el archivo",Toast.LENGTH_SHORT).show()
                }
            */

        }
*/


/*
 private fun uploadImageToFirebaseStorage(){
        if (seleccionar_foto == null) return

        val storage = FirebaseStorage.getInstance()

        val filename = UUID.randomUUID().toString()
        val ref =storageReference!!.child("images/"+UUID.randomUUID().toString())//ID para subir

     ref.putFile(filePath!!)
            .addOnSuccessListener {
                Log.d("Register", "Success upload img ${it.metadata?.path}")
                //

            }
            .addOnFailureListener{

            }




    }
*/


}








