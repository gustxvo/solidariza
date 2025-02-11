package br.com.gustavoalmeidacarvalho.solidariza.data.network

import br.com.gustavoalmeidacarvalho.solidariza.data.model.Distribuidor
import br.com.gustavoalmeidacarvalho.solidariza.data.model.DistribuidorInput
import br.com.gustavoalmeidacarvalho.solidariza.data.model.Registro
import br.com.gustavoalmeidacarvalho.solidariza.data.model.RegistroInput
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