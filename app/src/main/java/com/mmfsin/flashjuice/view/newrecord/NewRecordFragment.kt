package com.mmfsin.flashjuice.view.newrecord

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mmfsin.flashjuice.IListener
import com.mmfsin.flashjuice.R
import kotlinx.android.synthetic.main.fragment_new_record.*

class NewRecordFragment(private val listener: IListener, val level: Int) : Fragment(),
    NewRecordView {

    private lateinit var mContext: Context

    private val presenter by lazy { NewRecordPresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading.visibility = View.GONE

        levelReached.text = getString(R.string.levelNewRecord, level.toString())

        cancelButton.setOnClickListener { listener.closeNewRecordFragment(false) }
        okButton.setOnClickListener {
            if (userName.text.toString() != "") {
                okButton.isEnabled = false
                cancelButton.isEnabled = false
                loading.visibility = View.VISIBLE
                listener.closeKeyboard()

                presenter.writeNewRecordFragment(userName.text.toString(), level)
            }
        }
    }

    override fun newRecordWrote() {
        listener.closeNewRecordFragment(true)
    }

    override fun somethingWentWrong() {
        okButton.isEnabled = true
        cancelButton.isEnabled = true
        loading.visibility = View.GONE
        Toast.makeText(mContext, getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}