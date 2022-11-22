package com.raveline.borutoapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.domain.use_cases.heroes.HeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: HeroesUseCase
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery get() = _searchQuery

    private val _searchHeroes = MutableStateFlow<PagingData<HeroModel>>(PagingData.empty())
    val searchHeroes get() = _searchHeroes

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query.trim()
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch(IO) {
            useCase.invoke(query = query).cachedIn(viewModelScope).collect {
                _searchHeroes.value = it
            }
        }
    }
}