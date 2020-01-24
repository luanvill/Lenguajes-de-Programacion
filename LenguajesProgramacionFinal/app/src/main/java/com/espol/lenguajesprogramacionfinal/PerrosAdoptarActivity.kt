package com.espol.lenguajesprogramacionfinal

import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


// Access a Cloud Firestore instance from your Activity
val db = FirebaseFirestore.getInstance()
class PerrosAdoptarActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perros_adoptar)
        /*FirebaseAuth.getInstance().signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("AUTHENTICATION", "signInAnonymously:success")
                    val user = FirebaseAuth.getInstance().currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("AUTHENTICATION", "signInAnonymously:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }*/
        inicializarComponentes()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = FirebaseAuth.getInstance().currentUser
        updateUI(currentUser)
    }

    fun inicializarComponentes(){
        lateinit var listaPerrosAdoptar: ListView
        listaPerrosAdoptar  = findViewById(R.id.listaPerrosAdoptar)
        val titulo: TextView = findViewById(R.id.tituloPerrosAdoptar)

        cargarPerrosAdoptarInicial()

        titulo.text = "Perros Adopcion"
// 1
        val perrosList = PerrosAdoptarModelListView.PerrosAdopcion.getPerrosAdoptarAll()
// 2
        val adapter = PerrosAdoptarAdapter(this, perrosList)
        listaPerrosAdoptar.adapter = adapter


    }

    private fun cargarPerrosAdoptarInicial(){
        //llamar al web service
        val url : URL = URL("https://lenguajesdeprogramacion-f9944.firebaseio.com/perros")
        var urlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
        try{
            var entradaDatos: InputStream = BufferedInputStream(urlConnection.getInputStream())
            val texto = readStream(entradaDatos)
            print(texto)
            Log.i("PERROSADOPTAR","Saliendo al web service")
            //Toast.makeText(this,texto,Toast.LENGTH_LONG).show()
        }catch(e: NetworkOnMainThreadException) {
            print(e.message)
            Log.i("PERROSADOPTAR",e.message)
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

    private fun updateUI(user: FirebaseUser?) {
        /*hideProgressDialog()
        if (user != null) {
            mStatusTextView.setText(getString(R.string.google_status_fmt, user.email))
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.uid))
            findViewById(R.id.sign_in_button).setVisibility(View.GONE)
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE)
        } else {
            mStatusTextView.setText(R.string.signed_out)
            mDetailTextView.setText(null)
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE)
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE)
        }*/
    }
}