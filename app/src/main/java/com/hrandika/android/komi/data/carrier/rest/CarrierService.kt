package com.hrandika.android.komi.data.carrier.rest

import com.hrandika.android.komi.data.carrier.Carrier
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CarrierService {

    @GET("carriers")
    suspend fun findAll(): Response<CarriersHateoesPaged>

    @GET("carriers/{id}")
    fun findById(@Path("id") id: String): Response<Carrier>
}