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
        private val c = binding.root.context
        fun bind(record: Record, position: Int) {
            binding.apply {
                tvPosition.text = c.getString(R.string.ranking_number, position.toString())
                tvName.text = record.name
                tvRecord.text = record.record.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(records[position], position + 2)
    }

    override fun getItemCount(): Int = records.size
}