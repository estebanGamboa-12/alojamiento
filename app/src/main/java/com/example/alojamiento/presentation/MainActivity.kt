package com.example.alojamiento.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.alojamiento.R
import com.example.alojamiento.app.ErrorApp
import com.example.alojamiento.app.extension.loadUrl
import com.example.alojamiento.app.serialization.GsonSerialization
import com.example.alojamiento.data.HouseDataRepository
import com.example.alojamiento.data.local.LocalDataSource
import com.example.alojamiento.data.remote.RemoteDataSource
import com.example.alojamiento.databinding.ActivityMainBinding
import com.example.alojamiento.domain.GetHouseUseCase
import com.example.alojamiento.domain.House
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        MainViewModel(
            GetHouseUseCase(
                HouseDataRepository(
                    LocalDataSource(
                        this, GsonSerialization()
                    ), RemoteDataSource()
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupObserver()
        viewModel.loadHouse()

    }

    private lateinit var binding: ActivityMainBinding
    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupObserver() {
        val observer=Observer<MainViewModel.UiModel>{
            if (it.isLoading){

            }else{

            }
            it.error?.let {
                 showError(it)
            }
            it.house?.let{
                bindData(it)
        }
        }
        viewModel.uiModel.observe(this,observer)
    }
    private fun bindData(house: House){
        binding.apply {

            urlImage.loadUrl(house.url_image)
            description.text=house.description
              title.text=house.tilte
        }
    }
    private fun showError(error:ErrorApp){
        Snackbar.make(
            binding.root,
            getString(R.string.app_name),
            Snackbar.LENGTH_SHORT
        ).show()
    }

}