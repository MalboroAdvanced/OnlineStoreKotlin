package com.example.onlinestorekotlin

class EProduct {

    var id:Int
    var name:String
    var price:Int
    var pictureName:String

    constructor(id:Int,name:String,price:Int,picture:String){

        this.id=id
        this.name=name
        this.price=price
        this.pictureName=picture

    }
}