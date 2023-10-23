package com.example.alojamiento.data.remote

import com.example.alojamiento.app.Either
import com.example.alojamiento.app.ErrorApp
import com.example.alojamiento.app.left
import com.example.alojamiento.app.right
import com.example.alojamiento.data.remote.api.HouseApiModel
import com.example.alojamiento.data.remote.api.HouseApiService
import com.example.alojamiento.data.remote.api.toModel
import com.example.alojamiento.domain.House
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val baseUrl="https://dam.sitehub.es/curso-2023-2024/api/"

    suspend fun getHouse():Either<ErrorApp, House>{
        val retrofit=Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service:HouseApiService= retrofit.create(HouseApiService::class.java)

        try {
            val repos: Response<HouseApiModel> = service.getHouse()
            if (repos.isSuccessful) {
                //return repos.body()!!.toModel().right()
                return repos.body()!!.toModel().right()
            } else {
                return ErrorApp.UnkowError.left()
            }
        } catch (ex: Exception) {
            return ErrorApp.UnkowError.left()
        } catch (ex: Exception) {
            return ErrorApp.UnkowError.left()
        } catch (ex: Exception) {
            return ErrorApp.UnkowError.left()
        }
    }

}