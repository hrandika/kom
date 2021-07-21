package com.hrandika.android.komi.modulith.sim

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hrandika.android.komi.data.carrier.repository.CarrierRepository
import com.hrandika.android.komi.modulith.sim.domain.SimDetails
import timber.log.Timber


class SimViewModel @ViewModelInject constructor(
    private val carrierRepository: CarrierRepository
) : ViewModel() {

    val carriers = carrierRepository.findAll();

//    private val context = getApplication<Application>().applicationContext

    private val _sims = MutableLiveData<List<SimDetails>>()
    val sims: LiveData<List<SimDetails>> = _sims

    private val providerMap: MutableMap<String, PhoneAccountHandle>
    private lateinit var telecomManager: TelecomManager

    init {
        _sims.value = emptyList()
        providerMap = mutableMapOf()
//        getAvailableSims()
    }

//    @SuppressLint("MissingPermission")
//    fun getAvailableSims() {
//        Log.i("SimViewModel", "Getting sim informations")
//        val subsManager =
//            context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
//        val subsList = subsManager.activeSubscriptionInfoList
//
//        this.telecomManager =
//            context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
//        val list = telecomManager!!.callCapablePhoneAccounts
//
//        subsList.forEach {
//            run {
//                Log.i("SimViewModel", it.toString())
//                var sim = SimDetails()
//                sim.carrierName = it.carrierName.toString()
//                sim.iccId = it.iccId
//                sim.countryIso = it.countryIso
//                _sims.value = _sims.value?.plus(sim)
//
//                list.forEach { a ->
//                    if (a.id.contains(it.iccId))
//                        providerMap[sim.carrierName] = a
//                }
//            }
//        }
//    } // getAvailableSims()

    @SuppressLint("MissingPermission")
    fun onUpdate(sim: SimDetails) {
        Log.i("SimViewModel", "Getting sim informations ${sim.carrierName}")
        var ussdCode = when (sim.carrierName) {
            "Mobitel" -> "*100#"
            "Dialog" -> "#456#"
            else -> ""
        }
        val uri = Uri.fromParts("tel", ussdCode, "")
        val extras = Bundle()
        extras.putParcelable(
            TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE,
            providerMap.get(sim.carrierName)
        )
        telecomManager.placeCall(uri, extras)
    }

}