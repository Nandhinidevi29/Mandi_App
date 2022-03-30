package com.example.mandi

import android.os.Bundle
import android.view.View
import com.example.mandi.SellMyProduceSuccessFragment.Companion.PARAM_SELLER_INFO_DATA_MODEL
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SellMyProduceSuccessFragmentTest {
    private var sellMyProduceSuccessFragment: SellMyProduceSuccessFragment? = null

    @Before
    fun setUp() {
        sellMyProduceSuccessFragment = SellMyProduceSuccessFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PARAM_SELLER_INFO_DATA_MODEL, getSellerInfoDataModel())
            }
        }
        MandiTestHelper.startFragment(sellMyProduceSuccessFragment!!)
    }

    @Test
    fun testUI() {
        Assert.assertNotNull(sellMyProduceSuccessFragment)
        sellMyProduceSuccessFragment?.binding?.apply {
            Assert.assertEquals(fruitBoxImageView.visibility, View.VISIBLE)
            Assert.assertEquals(
                thankyouMessage.text,
                "Thank you Ram for selling your quality produce."
            )
            Assert.assertEquals(
                infoText.text,
                "Please ensure you received 23000.08 INR for 12 Tonnes of your produce."
            )
        }
    }

    private fun getSellerInfoDataModel() =
        SellMyProduceHelper.setSellerInfo(SellerInfo("Ram", "S100"), 12, 23000.08F)
}