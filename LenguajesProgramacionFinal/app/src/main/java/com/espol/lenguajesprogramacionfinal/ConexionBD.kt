package com.espol.lenguajesprogramacionfinal

import android.os.AsyncTask
import android.os.NetworkOnMainThreadException
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ConexionBD : AsyncTask<String,String,String>(){
    override fun doInBackground(vararg params: String?): String {
        FirebaseAuth.getInstance().signInAnonymously()
        val url : URL = URL(params[0])
        var urlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
        var resp = "" as String?
        try{
            urlConnection.connect()
            if(urlConnection.responseCode == HttpURLConnection.HTTP_OK){
                var entradaDatos: InputStream = BufferedInputStream(urlConnection.getInputStream())
                resp = readStream(entradaDatos)
                Log.i("CONEXIONBD","Web Service consumido exitosamente")
            }else{
                Log.i("CONEXIONBD","Hubo un error: "+urlConnection.responseCode)
            }

        }catch(e: Exception) {
            Log.i("PERROSADOPTAR",e.message)
        }finally{
            urlConnection.disconnect()
            if(resp != null){
                return resp
            }else{
                return "ERROR"
            }
        }
    }

    @Throws(IOException::class)
    private fun readStream(entrada: InputStream): String? {
        val sb = StringBuilder()
        val r = BufferedReader(InputStreamReader(entrada), 1000)
        var line: String? = r.readLine()
        while (line != null) {
            sb.append(line)
            line = r.readLine()
        }
        entrada.close()
        return sb.toString()
    }

}