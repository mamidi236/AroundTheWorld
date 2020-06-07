package com.raghav.aroundtheworld

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel:ViewModel() {

    val _showNavigation = MutableLiveData<Int>()
    val navigationDrawer = _showNavigation

    fun showNavigationBar(navigationGravity:Int){
        _showNavigation.value = navigationGravity
    }
}