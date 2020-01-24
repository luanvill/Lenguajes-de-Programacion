package com.espol.lenguajesprogramacionfinal

import android.app.ListActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class PerrosPerdidosActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PASO","Antes")
        setContentView(R.layout.activity_perros_perdidos)
        Log.d("PASO","Despues")
        inicializarComponentes()
    }

    fun inicializarComponentes(){
        lateinit var listaPerrosPerdidos: ListView
        Log.d("PASO","Llego hasta inicializar 2")
        listaPerrosPerdidos  = findViewById(R.id.listaPerrosPerdidos)
        Log.d("PASO","Llego hasta inicializar 3")
        val titulo: TextView = findViewById(R.id.tituloPerrosPerdidos)
        Log.d("PASO","Llego hasta inicializar 4")

        //cargarPerrosPerdidosInicial()

        titulo.text = "Perros Perdidos"
// 1
        val perrosList = PerrosPerdidosModelListView.PerrosPerdidos.getPerrosPerdidosAll()
// 2
        val adapter = PerrosPerdidosAdapter(this, perrosList)
        listaPerrosPerdidos.adapter = adapter


    }

    private fun cargarPerrosPerdidosInicial(){
        //llamar al web service
        val url : URL = URL("http://www.android.com/")
        var urlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
        try{
            var entradaDatos: InputStream = BufferedInputStream(urlConnection.getInputStream())
            readStream(entradaDatos)
        }finally{
            urlConnection.disconnect()
        }
    }

    @Throws(IOException::class)
    private fun readStream(`is`: InputStream): String? {
        val sb = StringBuilder()
        val r = BufferedReader(InputStreamReader(`is`), 1000)
        var line: String = r.readLine()
        while (line != null) {
            sb.append(line)
            line = r.readLine()
        }
        `is`.close()
        return sb.toString()
    }

    /*override protected fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        var itemSeleccionado =  getListAdapter().getItem(position)
        Toast.makeText(this,"Entro al item", Toast.LENGTH_LONG)
    }*/


}