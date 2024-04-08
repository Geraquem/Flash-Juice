package com.mmfsin.flashjuice.presentation.ranking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.databinding.ItemRecordBinding
import com.mmfsin.flashjuice.domain.models.Record

class RecordsAdapter(
    private val records: List<Record>
) : RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRecordBinding.bind(view)
        fun bind(record: Record) {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(records[position])
    }

    override fun getItemCount(): Int = records.size
}