package com.flytbase.madscalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flytbase.madscalculator.databinding.LayoutHistoryBinding

class CalculationHistoryAdapter(
    private val mContext: Context,
    private val calculationsList: ArrayList<String>?
) : RecyclerView.Adapter<CalculationHistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val mBinding = DataBindingUtil.inflate<LayoutHistoryBinding>(
            LayoutInflater.from(mContext)
            , R.layout.layout_history, parent, false
        )
        return HistoryViewHolder(mBinding.root)
    }

    override fun getItemCount(): Int {
        return calculationsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        if (!calculationsList.isNullOrEmpty()) {
            val historyItem: String? = calculationsList[position]

            holder.binding?.tvHistoryText?.text = historyItem

        }
    }


    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding: LayoutHistoryBinding? = DataBindingUtil.bind(itemView)
    }
}