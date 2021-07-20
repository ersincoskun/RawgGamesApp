package com.example.rawggamesapp.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.rawggamesapp.database.dao.GameDao
import com.example.rawggamesapp.database.db.GameDb
import com.example.rawggamesapp.model.Model
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class GameDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("testDatabase")
    lateinit var database: GameDb

    private lateinit var dao: GameDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.gameDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertGamesTest() = runBlockingTest {
        val games = listOf<Model.Game>(
            Model.Game(0, "", "", "", 2f, 0, "", 0, 0, 0),
            Model.Game(1, "", "", "", 2f, 0, "", 0, 0, 0)
        )
        dao.insertGames(*games.toTypedArray())
        val list = dao.getGames()
        assertThat(list).contains(games)
    }

}