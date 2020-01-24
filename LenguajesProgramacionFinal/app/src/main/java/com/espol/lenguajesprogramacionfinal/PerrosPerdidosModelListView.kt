package com.espol.lenguajesprogramacionfinal

class PerrosPerdidosModelListView(id:String, raza: String, sexo: String, edad: String, descr: String){
    var id: String
    var raza : String
    var sexo: String
    var edad: String
    var descr: String

    init{
        this.id = id
        this.raza = raza
        this.sexo = sexo
        this.edad = edad
        this.descr = descr
    }

    companion object PerrosPerdidos{
        fun getPerrosPerdidosAll():ArrayList<PerrosPerdidosModelListView> {
            var array = ArrayList<PerrosPerdidosModelListView>()
            array.add(PerrosPerdidosModelListView("1","Siberiano","Macho","Adulto","Color blanco y negro"))
            array.add(PerrosPerdidosModelListView("2","Golden","Hembra","Adulto","Color dorado"))
            return array
        }
    }

}