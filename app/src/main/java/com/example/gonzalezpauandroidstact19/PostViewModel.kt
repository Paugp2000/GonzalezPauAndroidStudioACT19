package com.example.gonzalezpauandroidstact19

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val apiService = RetrofitClient.apiService

    fun loadPosts() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = apiService.getPosts()
                if (response.isSuccessful) {
                    _posts.value = response.body()
                    _error.value = null
                } else {
                    _error.value = "Error HTTP: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}
}