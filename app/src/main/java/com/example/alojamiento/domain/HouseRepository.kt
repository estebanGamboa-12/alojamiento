package com.example.alojamiento.domain

import com.example.alojamiento.app.Either
import com.example.alojamiento.app.ErrorApp

interface HouseRepository {

    suspend fun obtain():Either<ErrorApp,House>
}