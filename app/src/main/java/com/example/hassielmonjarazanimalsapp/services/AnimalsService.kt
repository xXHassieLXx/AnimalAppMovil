package com.example.hassielmonjarazanimalsapp.services

import com.example.hassielmonjarazanimalsapp.models.Animals
import retrofit2.http.GET

interface AnimalsService {

    @GET("animals")
    suspend fun getAnimals() : Animals
}