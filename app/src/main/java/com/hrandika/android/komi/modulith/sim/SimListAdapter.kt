package com.hrandika.android.komi.modulith.sim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.hrandika.android.komi.R
import com.hrandika.android.komi.databinding.SimCardViewBinding
import com.hrandika.android.komi.modulith.sim.domain.SimDetails

class SimListAdapter internal constructor(
    val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: SimViewModel
) :
    RecyclerView.Adapter<SimListAdapter.SimViewHolder>() {

    private var sims = emptyList<SimDetails>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class SimViewHolder(
        itemView: View,
        private val binding: SimCardViewBinding,
        private val viewModel: SimViewModel,
        private val lifecycleOwner: LifecycleOwner
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: SimDetails) {
            // binding
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimViewHolder {
//        val itemView = inflater.inflate(R.layout.sim_card_view, parent, false)
//        val binding = SimCardViewBinding.inflate(inflater, parent, false)
        val binding: SimCardViewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.sim_card_view,
            parent,
            false
        )
        return SimViewHolder(binding.root, binding, viewModel, lifecycleOwner)
    }

    override fun onBindViewHolder(
        @NonNull holder: SimViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(sims[position])
    }


    override fun getItemCount(): Int {
        return sims.size
    }

    override fun onBindViewHolder(holder: SimViewHolder, position: Int) {
        val current = sims[position]
        holder.bind(current)
    }

    fun setSims(it: List<SimDetails>) {
        this.sims = it
        notifyDataSetChanged()
    }

}