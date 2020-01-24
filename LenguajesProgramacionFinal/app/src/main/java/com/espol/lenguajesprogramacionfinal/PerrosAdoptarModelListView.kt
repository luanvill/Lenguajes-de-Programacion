package com.espol.lenguajesprogramacionfinal

class PerrosAdoptarModelListView(id:String, raza: String, sexo: String, edad: String, descr: String){
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

    companion object PerrosAdopcion{
        fun getPerrosAdoptarAll():ArrayList<PerrosAdoptarModelListView> {
            var array = ArrayList<PerrosAdoptarModelListView>()
            array.add(PerrosAdoptarModelListView("1","Cholo","Macho","Cachorro","Cafe con manchas"))
            array.add(PerrosAdoptarModelListView("2","Dalmata","Hembra","Cachorro","Color dorado"))
            return array
        }
    }

}