package com.raveline.borutoapp.data.paging_source

import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import com.raveline.borutoapp.data.data_source.paging_datasource.HeroRemoteMediator
import com.raveline.borutoapp.data.database.HeroDatabase
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.data.remote.FakeSecondNarutoApi
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var narutoApi: FakeSecondNarutoApi
    private lateinit var heroDatabase: HeroDatabase

    @Before
    fun setUp() {
        narutoApi = FakeSecondNarutoApi()
        heroDatabase = HeroDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
    @Test
    fun refreshLoadReturnSuccessWhenMoreDataIsPresent() =
        runTest {
            val remoteMediator = HeroRemoteMediator(
                narutoApi = narutoApi,
                heroDatabase = heroDatabase
            )
            val pagingState = PagingState<Int, HeroModel>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        }

    @OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
    @Test
    fun refreshLoadReturnSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runTest {
            narutoApi.clearData()
            val remoteMediator = HeroRemoteMediator(
                narutoApi = narutoApi,
                heroDatabase = heroDatabase
            )
            val pagingState = PagingState<Int, HeroModel>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        }

    @OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
    @Test
    fun refreshLoadReturnErrorResultWhenErrorOccurs() =
        runTest {

            narutoApi.addException()

            val remoteMediator = HeroRemoteMediator(
                narutoApi = narutoApi,
                heroDatabase = heroDatabase
            )
            val pagingState = PagingState<Int, HeroModel>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Error)

        }

    @After
    fun tearDown() {
        heroDatabase.clearAllTables()
        heroDatabase.close()
    }
}