package com.example.crud

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clinic(
    var id:String?=null,
    var name:String?=null,
    var adress:String?=null,
    var photo:String?=null,
    var rate:Int?=null
):Parcelable


