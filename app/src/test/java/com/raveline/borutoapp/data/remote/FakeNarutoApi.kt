package com.raveline.borutoapp.data.remote

import com.raveline.borutoapp.data.model.ApiResponse
import com.raveline.borutoapp.data.model.HeroModel

class FakeNarutoApi : NarutoApi {
    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun searchHeroes(heroName: String): ApiResponse {
        val searchHeroes = findHeroes(heroName)
        return ApiResponse(
            success = true,
            message = "ok",
            heroes = searchHeroes
        )
    }

    private fun findHeroes(name: String): List<HeroModel> {
        val heroFounded = mutableListOf<HeroModel>()
        return if (name.isNotEmpty()) {
            heroes.forEach { hero ->
                if (hero.heroName.lowercase().contains(name.lowercase())) {
                    heroFounded.add(hero)
                }
            }
            heroFounded
        } else emptyList()
    }

    private val heroes = listOf(
        HeroModel(
            id = 1,
            heroImage = "",
            heroName = "Namikaze Minato",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "",
            day = "",
            heroNatureTypes = listOf()
        ),
        HeroModel(
            id = 2,
            heroImage = "",
            heroName = "Raveline Gliore",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "",
            day = "",
            heroNatureTypes = listOf()
        ),
        HeroModel(
            id = 3,
            heroImage = "",
            heroName = "Neji Hyuga",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "",
            day = "",
            heroNatureTypes = listOf()
        ),
        HeroModel(
            id = 4,
            heroImage = "",
            heroName = "Sasuke Uchiha",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "",
            day = "",
            heroNatureTypes = listOf()
        ),
        HeroModel(
            id = 5,
            heroImage = "",
            heroName = "Naruto Uzumaki",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "",
            day = "",
            heroNatureTypes = listOf()
        )
    )

}