package com.example.alojamiento.domain

import com.example.alojamiento.app.Either
import com.example.alojamiento.app.ErrorApp

class GetHouseUseCase(private  val repository: HouseRepository) {

    operator suspend fun invoke():Either<ErrorApp,House>{
        return  repository.obtain()
    }
}