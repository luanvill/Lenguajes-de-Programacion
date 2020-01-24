package com.espol.lenguajesprogramacionfinal

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


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

        var respuesta: String = ConexionBD().execute("https://lenguajesdeprogramacion-f9944.firebaseio.com/perros").get()


        titulo.text = "Perros Adopcion"
// 1
        val perrosList = PerrosAdoptarModelListView.PerrosAdopcion.getPerrosAdoptarAll()
// 2
        val adapter = PerrosAdoptarAdapter(this, perrosList)
        listaPerrosAdoptar.adapter = adapter


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