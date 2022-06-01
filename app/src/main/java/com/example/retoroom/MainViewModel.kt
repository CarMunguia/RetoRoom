package com.example.retoroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retoroom.database.DatabaseManager
import com.example.retoroom.database.MyCoroutines
import com.example.retoroom.database.User
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    val savedUsers = MutableLiveData<List<User>>()


    fun saveUser(user: User){
        viewModelScope.launch{
            val userDao = DatabaseManager.instance.database.userDao()
            MyCoroutines(userDao).save(user)
        }
    }

    fun getUsers(){
        viewModelScope.launch{
            val userDao = DatabaseManager.instance.database.userDao()
            savedUsers.value = MyCoroutines(userDao).getUsers().value
        }
    }

}