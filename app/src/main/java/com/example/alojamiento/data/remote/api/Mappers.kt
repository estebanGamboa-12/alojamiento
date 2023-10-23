package com.example.alojamiento.data.remote.api

import com.example.alojamiento.domain.House

fun HouseApiModel.toModel():House=
    House("1",this.title,this.description,this.url_image)