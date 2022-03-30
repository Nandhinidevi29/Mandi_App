package com.example.mandi

import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SellMyProduceFragmentTest {
    private var sellMyProduceFragment: SellMyProduceFragment? = null

    @Before
    fun setUp() {
        sellMyProduceFragment = SellMyProduceFragment()
        MandiTestHelper.startFragment(sellMyProduceFragment!!)
    }

    @Test
    fun testUI() {
        assertNotNull(sellMyProduceFragment)
        sellMyProduceFragment?.binding?.apply {
            assertEquals(
                mandiTitle.text,
                "Mandi"
            )
            assertEquals(
                sellMyProduceButton.text,
                "Sell my produce"
            )
            assertEquals(
                sellerName.titleText.text,
                "Seller name"
            )
            assertEquals(
                loyaltyCardInfo.titleText.text,
                "Loyalty card identifier"
            )
            assertEquals(
                village.titleText.text,
                "Village"
            )
            assertEquals(
                grossWeight.titleText.text,
                "Gross weight"
            )
            sellerName.textInput.setText("Ram")
            sellerName.textInput.clearFocus()
           verify(sellMyProduceFragment)?.checkSellerNameField()
        }
    }

    @Test
    fun verifyIfCheckSellerNameFieldIsCalled()
    {
        sellMyProduceFragment?.binding?.apply {
            sellerName.textInput.setText("Ram")
            sellerName.textInput.clearFocus()
            verify(sellMyProduceFragment)?.checkSellerNameField()
        }
    }

    @Test
    fun verifyIfCheckLocaltyIdFieldIsCalled()
    {
        sellMyProduceFragment?.binding?.apply {
            loyaltyCardInfo.textInput.setText("S100")
            loyaltyCardInfo.textInput.clearFocus()
            verify(sellMyProduceFragment)?.checkLoyaltyCardIdField()
        }
    }
}