package com.example.mandi

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SellMyProduceHelperTest {
    private val sellMyProduceHelper: SellMyProduceHelper

    init {
        sellMyProduceHelper = mock()
    }

    @Test
    fun testSetSellerInfo() {
        val sellerInfo = SellerInfo("Ram", "S100")
        whenever(sellMyProduceHelper.setSellerInfo(sellerInfo)).thenReturn(
            SellerInfoDataModel(
                sellerInfo, true, 12.08, villageInfo = listOf(Village("Ramnad", 12.08))
            )
        )
        assertNotNull(sellMyProduceHelper.setSellerInfo(any()))
    }

    @Test
    fun testCalculateGrossPrice() {
        whenever(sellMyProduceHelper.calculateGrossPrice(12.08, 1.12, 23)).thenReturn(
            12344.4
        )
        assertNotNull(sellMyProduceHelper.calculateGrossPrice(any(), any(), any()))
    }
}