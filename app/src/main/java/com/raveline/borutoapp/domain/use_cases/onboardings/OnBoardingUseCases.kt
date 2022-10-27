package com.raveline.borutoapp.domain.use_cases.onboardings

import com.raveline.borutoapp.data.repositoryImpl.Repository
import kotlinx.coroutines.flow.Flow

class OnBoardingUseCases(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }

    operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()
}