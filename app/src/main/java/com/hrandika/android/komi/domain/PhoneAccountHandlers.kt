package com.hrandika.android.komi.domain

import android.telecom.PhoneAccountHandle

data class PhoneAccountHandles(
    val primaryCarrierName: String?,
    val primary: PhoneAccountHandle?,
    val secondaryCarrierName: String?,
    val secondary: PhoneAccountHandle?
)