package com.example.alojamiento.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alojamiento.app.ErrorApp
import com.example.alojamiento.domain.GetHouseUseCase
import com.example.alojamiento.domain.House
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainViewModel(

    private val getHouseUseCase: GetHouseUseCase
):ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel> = _uiModel

            fun loadHouse(){
                _uiModel.value=UiModel(isLoading = true)

                viewModelScope.launch(Dispatchers.IO){

                    getHouseUseCase().fold(
                        {responseError(it)},{responseSuccess(it)}
                    )
                }
            }
    private fun responseError(error: ErrorApp) {
        _uiModel.postValue(UiModel(error = error))
    }

    private fun responseSuccess(house: House) {
        _uiModel.postValue(UiModel(house = house))
    }
    data class  UiModel(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val house: House? = null
    )





}