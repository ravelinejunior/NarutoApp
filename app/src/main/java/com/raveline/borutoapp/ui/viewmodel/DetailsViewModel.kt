package com.raveline.borutoapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.domain.use_cases.UseCases
import com.raveline.borutoapp.utils.Constants.heroId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<HeroModel?> = MutableStateFlow(null)
    val selectedHero: StateFlow<HeroModel?> get() = _selectedHero

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> get() = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> get() = _colorPalette

    private val TAG = "TAG${javaClass.name}"

    init {
        viewModelScope.launch(IO) {
            val heroId = savedStateHandle.get<Int>(heroId)
            _selectedHero.value = heroId?.let {
                useCase.heroesUseCase.invoke(heroId = heroId)
            }

            _selectedHero.value?.let {
                Log.i(TAG, "Hero: $it")
            }
        }
    }

    fun generateColorPalette() = viewModelScope.launch {
        _uiEvent.emit(UiEvent.GenerateColorPalette)
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }
}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}