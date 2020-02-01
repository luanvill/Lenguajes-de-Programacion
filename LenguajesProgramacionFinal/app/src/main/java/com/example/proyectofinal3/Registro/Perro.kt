package com.example.proyectofinal3.Registro

import com.google.firebase.database.IgnoreExtraProperties

// [START blog_user_class]
@IgnoreExtraProperties
data class Perro(
    var uid: String,
    var nombre: String?="",
    var raza: String?="",
    var edad: String?="",
    var sexo: String?="",
    var descripcion: String?=""
    //var fotoURL: String?=""
)
// [END blog_user_class]
