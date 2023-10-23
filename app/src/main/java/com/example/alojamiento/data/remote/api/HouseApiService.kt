package com.example.alojamiento.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface HouseApiService {

    @GET("youth-card-view.json")
    suspend fun getHouse():  Response<HouseApiModel>
}