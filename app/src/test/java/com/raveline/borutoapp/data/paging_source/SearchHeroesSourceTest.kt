package com.raveline.borutoapp.data.paging_source

import androidx.paging.PagingSource
import com.raveline.borutoapp.data.data_source.paging_datasource.SearchHeroesSource
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.data.remote.FakeNarutoApi
import com.raveline.borutoapp.data.remote.NarutoApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchHeroesSourceTest {

    private lateinit var narutoApi: NarutoApi
    private lateinit var heroes: List<HeroModel>

    @Before
    fun setUp() {
        narutoApi = FakeNarutoApi()
        heroes = listOf(
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Search_api_with_existing_hero_name Expected_hero_single_result Assert_LoadResult_Page`() =
        runTest {
            val heroesSource = SearchHeroesSource(narutoApi = narutoApi, query = "Namikaze")
            assertEquals<PagingSource.LoadResult<Int, HeroModel>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Search_api_with_existing_hero_name Expected_two_heroes_result Assert_LoadResult_Page`() =
        runTest {
            val heroesSource = SearchHeroesSource(narutoApi = narutoApi, query = "Na")
            assertEquals<PagingSource.LoadResult<Int, HeroModel>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first(), heroes.last()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Search_api_with_empty_hero_name Expected_none_hero Assert_LoadResult_Page`() =
        runTest {
            val heroesSource = SearchHeroesSource(narutoApi = narutoApi, query = "Na")
            val loadResult = heroesSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = narutoApi.searchHeroes("").heroes
            assertTrue(result.isEmpty())
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
}