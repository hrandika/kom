package com.hrandika.android.komi.modulith.keypad

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hrandika.android.komi.R
import com.hrandika.android.komi.databinding.FragmentKeypadBinding
import com.hrandika.android.komi.domain.PhoneAccountHandles
import com.hrandika.android.komi.utils.PhoneAccountHandlesProcessor

class KeypadFragment : Fragment() {

    private lateinit var binding: FragmentKeypadBinding
    private lateinit var viewModel: KeypadViewModel
    private lateinit var phoneAccountHandles: PhoneAccountHandles;
    private lateinit var telecomManager: TelecomManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.telecomManager =
            this.context?.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        this.phoneAccountHandles =
            PhoneAccountHandlesProcessor(this.context).process(this.telecomManager)

        viewModel = ViewModelProviders.of(this).get(KeypadViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_keypad,
            container,
            false
        )
        binding.keypadViewModel = viewModel
        binding.lifecycleOwner = this

        // Bind provider names
        var call0: View = binding.call0Button
        call0.findViewById<TextView>(R.id.provider_textView).text =
            this.phoneAccountHandles.primaryCarrierName

        val call1 = binding.call1Button
        call1.findViewById<TextView>(R.id.provider_textView).text =
            this.phoneAccountHandles.secondaryCarrierName

        // set observers
        viewModel.call.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                when (it.sim) {
                    0 -> this.makeCall(phoneAccountHandles.primary, it.phoneNumber)
                    1 -> this.makeCall(phoneAccountHandles.secondary, it.phoneNumber)
                }
            }
        })

        return binding.root
    }// onCreateView()

    @SuppressLint("MissingPermission")
    private fun makeCall(phoneAccountHandle: PhoneAccountHandle?, number: String?) {
        val uri = Uri.fromParts("tel", number, "")
        val extras = Bundle()
        extras.putParcelable(
            TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE,
            phoneAccountHandle
        )
        this.telecomManager.placeCall(uri, extras)
    }

}// class