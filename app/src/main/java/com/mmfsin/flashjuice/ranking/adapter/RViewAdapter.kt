package com.mmfsin.flashjuice.ranking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.databinding.RowRankingBinding
import com.mmfsin.flashjuice.ranking.model.RecordDTO

class RViewAdapter(private var records: List<RecordDTO>) :
    RecyclerView.Adapter<RViewAdapter.RecordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordHolder {
        return RecordHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_ranking, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecordHolder, position: Int) {
        holder.bind(records[position])
    }

    override fun getItemCount() = when {
        (records.size > 14) -> 15
        else -> records.size
    }

    class RecordHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bdg = RowRankingBinding.bind(view)
        fun bind(record: RecordDTO) {
            bdg.recordName.text = record.name
            bdg.recordLevel.text = record.level.toString()
        }
    }
}