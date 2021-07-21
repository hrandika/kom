package com.hrandika.android.komi.services

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.hrandika.android.komi.services.events.BalanceEvent
import org.greenrobot.eventbus.EventBus


class UssdService : AccessibilityService() {
    private val TAG = UssdService::class.simpleName

    override fun onInterrupt() {}

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.i(TAG, event?.text.toString())
        EventBus.getDefault().post(BalanceEvent("Rs 50"))
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "OnServiceConnected")
    }
}