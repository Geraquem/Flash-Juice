package com.mmfsin.flashjuice.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.mmfsin.flashjuice.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(), DashboardView {

    private val presenter by lazy { DashboardPresenter() }
    private lateinit var mContext: Context

    private lateinit var images: List<ImageView>

    var level = 1
    var lifes = 5

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        images = presenter.getImageViewList(mContext, activity)
        presenter.startGame(images)
    }

    override fun putBlackCircles() {
    }

    override fun putJuices() {
    }

    override fun putPoisons() {
    }

    override fun updateLevel() {
        levelText.text = level.toString()
    }

    override fun updateLifes() {
        lifesText.text = lifes.toString()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}