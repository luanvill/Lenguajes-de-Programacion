package com.example.proyectofinal3.Registro

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

// [START post_class]
@IgnoreExtraProperties
data class Post(
    var uid: String? = "",
    var nombre: String? = "",
    var raza: String? = "",
    var edad: String? = "",
    var sexo: String? = "",
    var descripcion: String? = ""
) {

    // [START post_to_map]
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "nombre" to nombre,
            "raza" to raza,
            "edad" to edad,
            "sexo" to sexo,
            "descripcion" to descripcion
        )
    }
    // [END post_to_map]
}
// [END post_class]