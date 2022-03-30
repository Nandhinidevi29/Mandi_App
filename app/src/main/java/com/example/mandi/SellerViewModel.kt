package com.example.mandi

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SellerViewModel : ViewModel() {
    private val sellerRepo = SellerRepository()

    fun insertSellerInfo(context: Context) {
        val sellerInfo = SellerInfo("Ram", "S100")
        CoroutineScope(Dispatchers.Main).launch {
            sellerRepo.insertSellerInfo(context, sellerInfo)
        }
    }

    fun getSellerByName(sellerName: String, context: Context): SellerInfo =
        sellerRepo.getSellerByName(context, sellerName)

    fun getSellerByLoyaltyCardId(loyaltyCardId: String, context: Context) =
        sellerRepo.getSellerByLoyaltyCardId(context, loyaltyCardId)
}