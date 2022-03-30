package com.example.mandi

import android.content.Context

class SellerRepository() {
    lateinit var sellerDao: SellerDao
    lateinit var dataBase: DataBase
    fun initializeDB(context: Context) {
        dataBase = DataBase.getInstance(context)
        sellerDao = dataBase.sellerDao()
    }

    suspend fun insertSellerInfo(context: Context, sellerInfo: SellerInfo) {
        initializeDB(context)
        return sellerDao.insertSeller(sellerInfo)
    }

    fun getSellerByName(context: Context, sellerName: String): SellerInfo {
        initializeDB(context)
        return sellerDao.getSellerByName(sellerName)
    }

    fun getSellerByLoyaltyCardId(context: Context, loyaltyCardId: String): SellerInfo {
        initializeDB(context)
        return sellerDao.getSellerByLoyaltyCardId(loyaltyCardId)
    }
}