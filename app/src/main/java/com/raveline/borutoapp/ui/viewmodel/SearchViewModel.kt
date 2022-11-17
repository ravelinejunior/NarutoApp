package com.raveline.borutoapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery get() = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query.trim()
    }
}