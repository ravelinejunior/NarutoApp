package com.raveline.borutoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingProcessState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}