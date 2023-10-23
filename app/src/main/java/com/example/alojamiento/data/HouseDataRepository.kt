package com.example.alojamiento.data

import com.example.alojamiento.app.Either
import com.example.alojamiento.app.ErrorApp
import com.example.alojamiento.data.local.LocalDataSource
import com.example.alojamiento.data.remote.RemoteDataSource
import com.example.alojamiento.domain.House
import com.example.alojamiento.domain.HouseRepository

class HouseDataRepository(
    private val local:LocalDataSource,
    private val remote:RemoteDataSource
):HouseRepository {
    override suspend fun obtain(): Either<ErrorApp, House> {
        var house=local.getHouse()
        house.mapLeft {
            return  remote.getHouse().map {
                local.save(it)
                it
            }
        }
        return house
    }
}