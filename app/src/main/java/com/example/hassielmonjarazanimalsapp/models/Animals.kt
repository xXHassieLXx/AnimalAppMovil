package com.example.hassielmonjarazanimalsapp.models

import com.google.gson.annotations.SerializedName

data class Animals(
    val description: String,
    val environmentId: String,
    val facts: List<String>,
    val id: String,
    val image: String,
    val imageGallery: List<String>,
    val name: String
)