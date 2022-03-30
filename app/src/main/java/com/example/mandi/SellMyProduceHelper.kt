package com.example.mandi

import android.text.InputType
import com.example.mandi.LoyaltyIndex.REGISTERED_SELLER
import com.example.mandi.LoyaltyIndex.UNREGISTERED_SELLER
import com.example.mandi.databinding.FragmentSellMyProduceBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object SellMyProduceHelper {
    fun setSellerCardTitle(binding: FragmentSellMyProduceBinding, fragment: SellMyProduceFragment) {
        binding.apply {
            sellerName.titleText.text = fragment.getString(R.string.seller_name)
            loyaltyCardInfo.titleText.text = fragment.getString(R.string.loyalty_card_id)
            village.titleText.text = fragment.getString(R.string.village)
            grossWeight.titleText.text = fragment.getString(R.string.gross_weight)
            grossWeight.textInput.inputType = InputType.TYPE_CLASS_NUMBER
            sellerName.textInput.isEnabled = true
            loyaltyCardInfo.textInput.isEnabled = true
        }
    }

    fun setSellerInfo(sellerInfo: SellerInfo, grossWeight: Int = 0, grossPrice: Float = 0F): SellerInfoDataModel =
        SellerInfoDataModel(
            sellerInfo,
            isRegisteredSeller(sellerInfo.loyaltyCardId),
            if (isRegisteredSeller(sellerInfo.loyaltyCardId)) REGISTERED_SELLER else UNREGISTERED_SELLER,
            grossWeight, grossPrice, listOf(Village("Ramnad", 12.08))
        )

    fun findPricePerKg(villageName: String, villageInfo: List<Village>) =
        villageInfo.filter { it.villageName.equals(villageName) }.first().pricePerKg

    fun calculateGrossPrice(pricePerKg: Double, loyaltyIndex: Double, grossWeight: Int) =
        (grossWeight * 1000) * loyaltyIndex * pricePerKg

    private fun isRegisteredSeller(loyaltyCardId: String? = null): Boolean {
        return loyaltyCardId?.let { true } ?: run { false }
    }
    fun roundOffDecimal(number: Double): Float =
        DecimalFormat("#.##", DecimalFormatSymbols(Locale.ROOT)).format(number).toFloat()
}