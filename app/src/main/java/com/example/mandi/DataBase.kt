package com.example.mandi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [SellerInfo::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun sellerDao(): SellerDao

    companion object {
        private var instance: DataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): DataBase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, DataBase::class.java,
                    SELLER_DATABASE
                ).allowMainThreadQueries().build()

            return instance!!
        }

    }
}
