package com.mmfsin.flashjuice.records

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mmfsin.flashjuice.R
import kotlinx.android.synthetic.main.fragment_records.*

class RecordsFragment() : Fragment() {

    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDDBB()
    }

    private fun getDDBB(){

        Firebase.database.reference.child("records").child("playerOne").get().addOnSuccessListener {

            record.text = it.value.toString()

        }.addOnFailureListener{

            record.text = "DATABASE ERROR"

        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}