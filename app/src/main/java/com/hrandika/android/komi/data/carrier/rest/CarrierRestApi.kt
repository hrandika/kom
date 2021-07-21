package com.hrandika.android.komi.data.carrier.rest

import com.hrandika.android.komi.data._common.BaseDataSource

import javax.inject.Inject

class CarrierRestApi @Inject constructor(
    private val carrierService: CarrierService
) : BaseDataSource() {

    suspend fun findAll() = getResult { carrierService.findAll() }
    suspend fun findById(id: String) = getResult { carrierService.findById(id) }
}