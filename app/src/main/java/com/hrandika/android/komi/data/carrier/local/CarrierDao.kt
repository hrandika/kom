package com.hrandika.android.komi.data.carrier.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hrandika.android.komi.data.carrier.Carrier

@Dao
interface CarrierDao {

    @Query("SELECT * FROM carrier")
    fun findAll() : LiveData<List<Carrier>>

    @Query("SELECT * FROM carrier WHERE id = :id")
    fun findById(id: String): LiveData<Carrier>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(carrier: Carrier)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(carrier: List<Carrier>)
}