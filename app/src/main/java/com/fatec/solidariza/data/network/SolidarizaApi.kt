package com.fatec.solidariza.data.network

import com.fatec.solidariza.data.model.Distribuidor
import com.fatec.solidariza.data.model.DistribuidorInput
import com.fatec.solidariza.data.model.Registro
import com.fatec.solidariza.data.model.RegistroInput
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SolidarizaApiService {

    @GET("distribuidores")
    suspend fun getDistribuidores(): List<Distribuidor>

    @POST("/distribuidor")
    suspend fun saveDistribuidor(@Body distribuidor: DistribuidorInput): Distribuidor

    @GET("distribuidor/{distribuidor_id}/registros")
    suspend fun getRegistrosByDistribuidorId(@Path("distribuidor_id") id: Int): List<Registro>

    @POST("registro/{distribuidor_id}")
    suspend fun saveRegistro(
        @Path("distribuidor_id") distribuidorId: Int,
        @Body registro: RegistroInput,
    ): Registro
}