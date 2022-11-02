package com.raveline.borutoapp.domain.use_cases

import com.raveline.borutoapp.domain.use_cases.heroes.HeroesUseCase
import com.raveline.borutoapp.domain.use_cases.onboardings.OnBoardingUseCases

data class UseCases(
    val onBoardingUseCases: OnBoardingUseCases,
    val heroesUseCase: HeroesUseCase
)
