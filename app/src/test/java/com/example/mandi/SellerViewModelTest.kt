package com.example.mandi

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner::class)
class SellerViewModelTest {
    lateinit var dataBase: DataBase
    private var viewModel: SellerViewModel? = null
    private var sellerRepository: SellerRepository = mockk(relaxed = true)
    lateinit var context: Context
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dataBase = Room.inMemoryDatabaseBuilder(
            context, DataBase::class.java
        ).allowMainThreadQueries().build()
        MockitoAnnotations.initMocks(this)
        viewModel = SellerViewModel()

    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun testGetSellerByName() {
        viewModel!!.getSellerByName("Ram", context)
        verify(sellerRepository.getSellerByName(any(), any()))
    }

    @Test
    fun testGetSellerByLoyaltyCardId()
    {
        viewModel!!.getSellerByLoyaltyCardId("S100", context)
        val sellerInfo = sellerRepository.getSellerByLoyaltyCardId(any(), any())
        assertNotNull(sellerInfo)
    }
}