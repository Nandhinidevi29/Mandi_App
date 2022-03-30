package com.example.mandi

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val EMPTY = ""
const val SELLER_TABLE_NAME = "seller"
const val SELLER_DATABASE = "seller_database"

@Parcelize
@Entity(tableName = "seller")
data class SellerInfo(
    @PrimaryKey
    val name: String,
    val loyaltyCardId: String? = null
) : Parcelable

object LoyaltyIndex {
    const val REGISTERED_SELLER = 1.12
    const val UNREGISTERED_SELLER = 0.98
}

@Parcelize
data class SellerInfoDataModel(
    val sellerInfo: SellerInfo,
    val isRegisteredSeller: Boolean = false,
    val loyaltyIndex: Double = 0.0,
    val grossWeight: Int = 0,
    var grossPrice: Float = 0F,
    val villageInfo: List<Village>
) : Parcelable

@Parcelize
data class Village(
    val villageName: String,
    val pricePerKg: Double
) : Parcelable