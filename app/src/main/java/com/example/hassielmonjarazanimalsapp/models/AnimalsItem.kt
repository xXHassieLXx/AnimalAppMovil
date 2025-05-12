package com.example.hassielmonjarazanimalsapp.models

data class AnimalsItem(
    val description: String,
    val environmentId: String,
    val facts: List<String>,
    val id: String,
    val image: String,
    val imageGallery: List<String>,
    val name: String
)