package com.example.musicplayer

import com.example.domain.model.MusicItem
import com.example.domain.usecase.MusicUseCase
import com.example.musicplayer.helper.getOrAwaitValue
import com.example.musicplayer.viewmodel.MusicViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

//@RunWith(RobolectricTestRunner::class)
//@Config(manifest = Config.NONE)
//class MusicViewModelTest {
//    @OptIn(ExperimentalCoroutinesApi::class)
//    private val testDispatcher = UnconfinedTestDispatcher()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Before
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `get list music`() = runTest {
//        val mokeUseCase = mock<MusicUseCase>()
//        val expected = listOf(
//            MusicItem(1, "Journey ", "Taylor Swift", "https://music.apple.com/us/album/dont-stop-believin-2024-remaster/169003304?i=169003415&uo=4", "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/71/2d/61/712d617d-f4a4-5904-1b11-d4b4b45c47c5/828768588925.jpg/100x100bb.jpg",null),
//            MusicItem(2, "Journey ", "Bruno Mars", "https://music.apple.com/us/album/dont-stop-believin-2024-remaster/169003304?i=169003415&uo=4", "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/71/2d/61/712d617d-f4a4-5904-1b11-d4b4b45c47c5/828768588925.jpg/100x100bb.jpg",null)
//        )
//        val viewModel = MusicViewModel(mokeUseCase)
//        viewModel.music()
//        viewModel.search("taylor swift")
//        assertEquals(expected, viewModel.musics.getOrAwaitValue())
//    }
//}
