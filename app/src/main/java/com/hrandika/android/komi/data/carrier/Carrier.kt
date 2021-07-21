package com.hrandika.android.komi.data.carrier

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrier")
data class Carrier(
    @PrimaryKey
    val id: String,
    val name: String,
    val countryCode: String
)