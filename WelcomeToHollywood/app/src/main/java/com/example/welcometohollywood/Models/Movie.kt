package com.example.welcometohollywood.Models

class Movie {
    var id: Int = 0
    var area: Int = 0
    var nameFr: String = ""
    var nameEn: String = ""

    constructor(id: Int, area: Int, nameFr: String, nameEn: String) {
        this.id = id
        this.area = area
        this.nameFr = nameFr
        this.nameEn = nameEn
    }

    fun compareName(name: String): Boolean {
        println(this.id.toString()+" - "+this.nameFr)
        if(!this.nameFr.toLowerCase().equals(name.toLowerCase())) {
            return this.nameEn.toLowerCase().equals(name.toLowerCase())
        }
        return true
    }

}