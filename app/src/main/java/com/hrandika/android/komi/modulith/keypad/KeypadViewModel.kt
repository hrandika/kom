package com.hrandika.android.komi.modulith.keypad

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hrandika.android.komi.modulith.keypad.padlayout.DialPadKey
import com.hrandika.android.komi.utils.Event
import com.hrandika.android.komi.utils.SimAndNumber

class KeypadViewModel() : ViewModel() {

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> get() = _phoneNumber

    private val _call = MutableLiveData<Event<SimAndNumber>>()
    val call: LiveData<Event<SimAndNumber>> get() = _call

    init {
        _phoneNumber.value = "071 123 4567"
    }

    fun onNumber(view: View) {
        var dialPadKey = view as DialPadKey;

        if (_phoneNumber.value != null) {
            _phoneNumber.value = _phoneNumber.value + dialPadKey.numberText
            if (_phoneNumber.value?.length == 3 || _phoneNumber.value?.length == 7)
                _phoneNumber.value = _phoneNumber.value + " "
        } else
            _phoneNumber.value = dialPadKey.numberText
    }

    fun onBack() {
        val text = _phoneNumber!!.value
        _phoneNumber.value = text?.dropLast(1)?.trim()
    }

    fun onBackLongPress(view: View): Boolean {
        _phoneNumber.value = ""
        return false
    }

    fun call0() {
        val call = SimAndNumber(0,this._phoneNumber.value)
        this._call.value = Event(call)
    }

    fun call1() {
        val call = SimAndNumber(1,this._phoneNumber.value)
        this._call.value = Event(call)
    }
}