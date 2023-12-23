package com.register.coffeapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.register.coffeapp.domain.entities.User
import com.register.coffeapp.domain.entities.Cafe
import com.register.coffeapp.domain.entities.MenuItem
import com.register.coffeapp.domain.useCase.GetCafeInfoUseCase
import com.register.coffeapp.domain.useCase.GetMenuUseCase
import com.register.coffeapp.domain.useCase.RegisterUseCase
import com.register.coffeapp.domain.useCase.SignInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCafeInfoUseCase: GetCafeInfoUseCase,
    private val getMenuUseCase: GetMenuUseCase,
    private val registerUseCase: RegisterUseCase,
    private val signInUseCase: SignInUseCase,
): ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _cafeList = MutableLiveData<List<Cafe>>()
    val cafeList: LiveData<List<Cafe>> = _cafeList

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _menuList = MutableLiveData<List<MenuItem>>()
    val menuList: LiveData<List<MenuItem>> = _menuList

    fun register(user: User) {
        viewModelScope.launch {
            val userData = registerUseCase.register(user)
            _error.value = userData.token != ""
        }
    }

    fun signIn(user: User) {
        viewModelScope.launch {
            val userData = signInUseCase.signIn(user)
            _error.value = userData.token != ""
        }
    }

    fun getCafeInfo(token: String) {
        viewModelScope.launch {
            val cafeList = getCafeInfoUseCase.getCafeInfo(token)
            Log.d("MyTag", "$cafeList")
            _cafeList.value = cafeList
        }
    }

    fun getMenu(token: String, id: String) {
        viewModelScope.launch {
            val menu = getMenuUseCase.getMenuItem(token, id)
            Log.d("MyTag", "$menu")
            _menuList.value = menu
        }
    }

}