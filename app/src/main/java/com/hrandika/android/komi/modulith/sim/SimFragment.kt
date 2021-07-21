package com.hrandika.android.komi.modulith.sim

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hrandika.android.komi.R
import com.hrandika.android.komi.databinding.FragmentSimBinding
import com.hrandika.android.komi.services.events.BalanceEvent
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

@AndroidEntryPoint
class SimFragment : Fragment() {

    private val viewModel: SimViewModel by viewModels()
    private lateinit var binding: FragmentSimBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sim,
            container,
            false
        )

        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.sims_recycleView)
        val adapter = SimListAdapter(binding.root.context, this, viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

//        viewModel.sims.observe(viewLifecycleOwner, Observer { sims ->
//            // Update the cached copy of the words in the adapter.
//            Log.i(this.javaClass.simpleName, "Got new sims ${sims?.size}")
//            sims?.let { adapter.setSims(it) }
//        })

        viewModel.carriers.observe(viewLifecycleOwner, Observer {
            Timber.i("Size of the response :${it.data?.size}")
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: BalanceEvent) {
        Log.i(this.javaClass.simpleName, "Got event ${event.balance}")
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}