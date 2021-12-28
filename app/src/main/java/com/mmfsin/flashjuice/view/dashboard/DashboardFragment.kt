package com.mmfsin.flashjuice.view.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mmfsin.flashjuice.IListener
import com.mmfsin.flashjuice.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.include_result_bad.*
import kotlinx.android.synthetic.main.include_result_bad.view.*
import kotlinx.android.synthetic.main.include_result_good.*
import kotlinx.android.synthetic.main.include_result_good.view.*

class DashboardFragment(val listener: IListener, val savedHighScore: Int) : Fragment(),
    DashboardView {

    private val juiceTAG = "JUICE"
    private val poison1TAG = "POISON1"
    private val poison2TAG = "POISON2"
    private val poison3TAG = "POISON3"

    private val presenter by lazy { DashboardPresenter(this) }
    private lateinit var mContext: Context

    private lateinit var images: List<ImageView>
    private lateinit var endJuices: List<ImageView>

    private lateinit var goodPhrases: List<String>
    private lateinit var badPhrases: List<String>

    private var level = 1
    private var lifes = 5
    private var numJuices = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goodPhrases = presenter.getResultPhrases(mContext, true)
        badPhrases = presenter.getResultPhrases(mContext, false)
        endJuices = presenter.getJuicesImageViewList(mContext, activity)

        images = presenter.getImageViewList(mContext, activity)
        presenter.startGame(images, level)

        goodResult.nextLevel.setOnClickListener {
            showGoodResult(View.GONE)
            numJuices = 0
            level++
            presenter.startGame(images, level)
        }

        badResult.retryButton.setOnClickListener {
            showBadResult(View.GONE)
            numJuices = 0
            level = 1
            lifes = 5
            presenter.startGame(images, level)
        }
    }

    override fun updateLevel() {
        levelText.text = getString(R.string.level, level.toString())
    }

    override fun updateLifes() {
        lifesText.text = lifes.toString()
    }

    override fun putBlackCircles() {
        for (image in images) {
            image.setImageResource(R.drawable.ic_black_circle)
        }
    }

    override fun putJuices(juices: List<Int>) {
        for (i in juices) {
            images[i].setImageResource(R.drawable.ic_juice)
            images[i].tag = juiceTAG
        }
    }

    override fun putPoisons(poisons: List<Int>, phase: Int) {
        for (i in poisons) {
            when ((0..phase).random()) {
                0 -> {
                    images[i].setImageResource(R.drawable.ic_poison_one)
                    images[i].tag = poison1TAG
                }
                1 -> {
                    images[i].setImageResource(R.drawable.ic_poison_two)
                    images[i].tag = poison2TAG
                }
                2 -> {
                    images[i].setImageResource(R.drawable.ic_poison_three)
                    images[i].tag = poison3TAG
                }
            }
        }
    }

    override fun setImageViewListeners() {
        for (image in images) {
            image.setOnClickListener {
                if (image.tag == juiceTAG) {
                    imageOnClick(image, true)
                } else {
                    imageOnClick(image, false)
                }
            }
        }
    }

    private fun imageOnClick(image: ImageView, isGoodClick: Boolean) {
        if (isGoodClick) {
            numJuices++
            image.setImageResource(R.drawable.ic_juice)
        } else {
            lifes--
            when (image.tag) {
                poison1TAG -> image.setImageResource(R.drawable.ic_poison_one)
                poison2TAG -> image.setImageResource(R.drawable.ic_poison_two)
                else -> image.setImageResource(R.drawable.ic_poison_three)
            }

        }
        image.isClickable = false
        presenter.updateUI(lifes, numJuices, level)
    }

    override fun showGoodResult(view: Int) {
        goodResultText.text = presenter.setPhrase(goodPhrases)
        goodResultLevelText.text = getString(R.string.levelCompleted, level.toString())
        goodResult.visibility = view
    }

    override fun showBadResult(view: Int) {
        badResultText.text = presenter.setPhrase(badPhrases)
        badResultLevelText.text = getString(R.string.levelFailed, level.toString())
        presenter.setJuiceErrors(endJuices, numJuices)
        badResult.visibility = view
    }

    override fun checkHighScore(isGameEnd: Boolean) {
        if (level > savedHighScore) {
            listener.putNewHighScore(level)
            if (isGameEnd) {
                Toast.makeText(mContext, getString(R.string.newRecord), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showNewRecordFragment() {
        listener.showNewRecordFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}