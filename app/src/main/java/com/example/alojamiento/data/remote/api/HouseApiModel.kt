package com.example.alojamiento.data.remote.api

import com.google.gson.annotations.SerializedName

data class HouseApiModel(

    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url_image") val url_image: String,


    )
