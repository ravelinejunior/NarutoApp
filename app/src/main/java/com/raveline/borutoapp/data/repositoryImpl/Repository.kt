package com.raveline.borutoapp.data.repositoryImpl

import com.raveline.borutoapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingProcessState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()
}