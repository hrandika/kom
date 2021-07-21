package com.hrandika.android.komi.utils

import android.annotation.SuppressLint
import android.content.Context
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.telephony.SubscriptionManager
import com.hrandika.android.komi.domain.PhoneAccountHandles

class PhoneAccountHandlesProcessor(val context: Context?) {

    @SuppressLint("MissingPermission")
    fun process(telecomManager:TelecomManager): PhoneAccountHandles {
        var primarySimId = ""
        var secondarySimId = ""
        var primaryCarrierName = ""
        var secondaryCarrierName =""

        val manager =
            context?.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
        val subsList = manager.activeSubscriptionInfoList
        var index = -1
        for (subscriptionInfo in subsList) {
            index++
            if (index == 0) {
                primarySimId = subscriptionInfo.iccId
                primaryCarrierName = subscriptionInfo.carrierName.toString()
            } else{
                secondarySimId = subscriptionInfo.iccId
                secondaryCarrierName = subscriptionInfo.carrierName.toString()
            }

        }

        val list = telecomManager!!.callCapablePhoneAccounts
        var primaryPhoneAccountHandle: PhoneAccountHandle? = null
        var secondaryPhoneAccountHandle: PhoneAccountHandle? = null

        for (phoneAccountHandle in list) {
            if (phoneAccountHandle.id.contains(primarySimId))
                primaryPhoneAccountHandle = phoneAccountHandle

            if (phoneAccountHandle.id.contains(secondarySimId))
                secondaryPhoneAccountHandle = phoneAccountHandle
        }

        return PhoneAccountHandles(
            primaryCarrierName  = primaryCarrierName,
            secondaryCarrierName = secondaryCarrierName,
            primary = primaryPhoneAccountHandle,
            secondary = secondaryPhoneAccountHandle
        )
    }
}