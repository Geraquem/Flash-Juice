package com.mmfsin.flashjuice.dashboard

import android.content.Context
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.mmfsin.flashjuice.dashboard.helper.DashBoardHelper
import com.mmfsin.flashjuice.dashboard.helper.EndGameHelper

class DashboardPresenter(private val view: DashboardView) {

    private val helper by lazy { DashBoardHelper() }
    private val endGameHelper by lazy { EndGameHelper() }

    private fun clickableImages(images: List<ImageView>, isClickable: Boolean) =
        helper.clickableImages(images, isClickable)

    fun getResultPhrases(context: Context, isGood: Boolean): List<String> {
        return helper.getResultPhrases(context, isGood)
    }

    fun getImageViewList(context: Context, activity: FragmentActivity?): List<ImageView> {
        return helper.getImageViewList(context, activity)
    }

    fun getJuicesImageViewList(context: Context, activity: FragmentActivity?): List<ImageView> {
        return endGameHelper.getFiveJuices(context, activity)
    }

    fun startGame(images: List<ImageView>, level: Int) {
        view.updateLevel()
        view.updateLifes()
        view.putBlackCircles()

        clickableImages(images, false)

        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                firstPhase(images, level)
            }
        }.start()
    }

    private fun firstPhase(images: List<ImageView>, level: Int) {
        val juices = helper.getJuicyList()
        view.putJuices(juices)

        val poisons = helper.getPoisonList(juices)
        when {
            level < 2 -> view.putPoisons(poisons, 0)
            level < 3 -> view.putPoisons(poisons, 1)
            else -> view.putPoisons(poisons, 2)
        }

        secondPhase(images, level)
    }

    private fun secondPhase(images: List<ImageView>, level: Int) {
        object : CountDownTimer(helper.getDisappearedTime(level), 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                view.putBlackCircles()
                clickableImages(images, true)
                view.setImageViewListeners()
            }
        }.start()
    }

    fun updateUI(life: Int, numJuices: Int) {
        if (life <= 0) {
            view.showBadResult(View.VISIBLE)
            view.checkHighScore(true)
        }
        if (numJuices == 5) {
            view.checkHighScore(false)
            view.showGoodResult(View.VISIBLE)
        }
        view.updateLifes()
    }

    fun setPhrase(phrases: List<String>): String {
        return phrases[(phrases.indices).random()]
    }

    fun setJuiceErrors(endJuices: List<ImageView>, numJuices: Int) {
        endGameHelper.setJuiceErrors(endJuices, numJuices)
    }
}