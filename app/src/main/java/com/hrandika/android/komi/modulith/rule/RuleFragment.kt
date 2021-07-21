package com.hrandika.android.komi.modulith.rule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hrandika.android.komi.R
import com.hrandika.android.komi.databinding.FragmentRulesBinding

class RuleFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding
    private lateinit var viewModel: RuleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RuleViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rules,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }
}