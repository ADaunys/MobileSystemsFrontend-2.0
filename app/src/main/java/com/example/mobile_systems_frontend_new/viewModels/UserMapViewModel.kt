package com.example.mobile_systems_frontend_new.viewModels

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_systems_frontend_new.model.UserMap
import com.example.mobile_systems_frontend_new.repository.DataRepository
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserMapViewModel(private val repository: DataRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val users: LiveData<UserMap> = repository.user.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(userMap: UserMap) = viewModelScope.launch {
        repository.insert(userMap)
    }
}

class WordViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserMapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserMapViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}