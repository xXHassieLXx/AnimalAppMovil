package com.example.hassielmonjarazanimalsapp.models
import com.google.gson.annotations.SerializedName

data class Ambientes(
    @SerializedName("_id") val id: String,
    val description: String,
    val image: String,
    val name: String
)