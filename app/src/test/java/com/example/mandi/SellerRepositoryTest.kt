package com.example.mandi

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@Suppress("LeakingThis")
class SellerRepositoryTest {
    lateinit var sellerRepository: SellerRepository
    lateinit var dataBase: DataBase
    lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dataBase = Room.inMemoryDatabaseBuilder(
            context, DataBase::class.java
        ).allowMainThreadQueries().build()
        MockitoAnnotations.initMocks(this)
        sellerRepository = SellerRepository()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun testIfDaoGetSellerByNameIsCalled() {
        whenever(dataBase.sellerDao().getSellerByName("Ram")).thenReturn(
            SellerInfo("Ram", "S100")
        )
        sellerRepository.getSellerByName(context, "Ram")
        val sellerInfo = dataBase.sellerDao().getSellerByName("Ram")
        assertNotNull(sellerInfo)
    }
}