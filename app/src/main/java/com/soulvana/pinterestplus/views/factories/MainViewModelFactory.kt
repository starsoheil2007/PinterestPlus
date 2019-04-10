package com.soulvana.pinterestplus.views.factories


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soulvana.pinterestplus.view_models.MainActivityViewModel
import com.soulvana.pinterestplus.view_models.SplashActivityViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory (private val activity: AppCompatActivity) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SplashActivityViewModel::class.java)) {
            return SplashActivityViewModel() as T
        }

        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}