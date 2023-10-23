package com.example.alojamiento.data.local

import android.content.Context
import com.example.alojamiento.app.Either
import com.example.alojamiento.app.ErrorApp
import com.example.alojamiento.app.left
import com.example.alojamiento.app.right
import com.example.alojamiento.app.serialization.JsonSerialization
import com.example.alojamiento.domain.House

class LocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {

    private val sharedPref=context.getSharedPreferences("house",Context.MODE_PRIVATE)
    private val houseId="1"


    fun getHouse():Either<ErrorApp, House> {
        val jsonHouse=sharedPref.getString(houseId,null)
        jsonHouse?.let {
            return  serialization.fromJson(it,House::class.java).right()
        }
        return ErrorApp.UnkowError.left()

    }
    fun save(house: House){
        sharedPref.edit().apply{
            putString(houseId,serialization.toJson(house,House::class.java))
            apply()
        }
    }




}