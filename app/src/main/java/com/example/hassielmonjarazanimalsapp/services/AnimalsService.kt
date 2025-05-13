package com.example.hassielmonjarazanimalsapp.services

import android.os.Environment
import com.example.hassielmonjarazanimalsapp.models.Ambientes
import com.example.hassielmonjarazanimalsapp.models.Animals
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimalsService {

    @GET("animals")
    suspend fun getAnimals(): List<Animals>

    @GET("animals/{id}")
    suspend fun getAnimalById( @Path("id") id:String) : Animals

    @GET("animals")
    suspend fun getAnimalByEnvironmentId(
        @Query("environmentId") environmentId: String,
    ) : List<Animals>

    @GET("environments")
    suspend fun getEnvironments(): List<Ambientes>

    @GET("environments/{id}")
    suspend fun getEnvironmentById ( @Path("id") id: String) : Ambientes
}