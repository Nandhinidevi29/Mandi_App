package com.example.mandi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SellerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeller(seller: SellerInfo)

    @Query("SELECT * FROM $SELLER_TABLE_NAME where name = :name")
    fun getSellerByName(name: String): SellerInfo

    @Query("SELECT * FROM $SELLER_TABLE_NAME where loyaltyCardId = :loyaltyCardId")
    fun getSellerByLoyaltyCardId(loyaltyCardId: String): SellerInfo
}