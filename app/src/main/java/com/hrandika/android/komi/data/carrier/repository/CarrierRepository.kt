package com.hrandika.android.komi.data.carrier.repository

import com.hrandika.android.komi.data._common.performGetOperation
import com.hrandika.android.komi.data.carrier.local.CarrierDao
import com.hrandika.android.komi.data.carrier.rest.CarrierRestApi
import javax.inject.Inject

class CarrierRepository @Inject constructor(
    private val restApi: CarrierRestApi,
    private val dao: CarrierDao
) {

    fun findAll() = performGetOperation(
        databaseQuery = { dao.findAll() },
        networkCall = { restApi.findAll() },
        saveCallResult = { dao.saveAll(it._embedded.carriers) }
    )

    fun findById(id: String) = performGetOperation(
        databaseQuery = { dao.findById(id) },
        networkCall = { restApi.findById(id)},
        saveCallResult = {dao.save(it)}
    )
}