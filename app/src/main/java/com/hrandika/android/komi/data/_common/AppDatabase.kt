package com.hrandika.android.komi.data._common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hrandika.android.komi.data.carrier.Carrier
import com.hrandika.android.komi.data.carrier.local.CarrierDao

@Database(entities = [Carrier::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carrierDao(): CarrierDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "carrier")
                .fallbackToDestructiveMigration()
                .build()
    }

}