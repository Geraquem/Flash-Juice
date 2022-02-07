package com.mmfsin.flashjuice.view.dashboard

import android.content.Context
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.mmfsin.flashjuice.repository.FirebaseRepo
import com.mmfsin.flashjuice.view.dashboard.helper.DashBoardHelper
import com.mmfsin.flashjuice.view.dashboard.helper.EndGameHelper
import com.mmfsin.flashjuice.view.ranking.model.RecordDTO

class DashboardPresenter(private val view: DashboardView) : FirebaseRepo.IRanking {

    private val helper by lazy { DashBoardHelper() }
    private val endGameHelper by lazy { EndGameHelper() }

    private val repository by lazy { FirebaseRepo(this) }

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
            level < 5 -> view.putPoisons(poisons, 0)
            level < 10 -> view.putPoisons(poisons, 1)
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

    fun updateUI(life: Int, numJuices: Int, level: Int) {
        if (life <= 0) {
            view.showBadResult(View.VISIBLE)
            view.checkHighScore(true)
            repository.getRankingLevels(level)
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

    override fun returnTopLevels(level: Int, levels: List<Long>) {
        for (recordLevel in levels) {
            if (level >= recordLevel) {
                view.showNewRecordFragment(level)
                break
            }
        }
    }

    override fun returnRecords(records: List<RecordDTO>) {}
    override fun newRecordWrote() {}
    override fun resultKo() {}
}