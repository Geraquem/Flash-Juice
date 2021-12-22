package com.mmfsin.flashjuice.dashboard

import android.content.Context
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.mmfsin.flashjuice.R

class DashboardPresenter {

    private val helper by lazy { DashBoardListHelper() }

    fun getImageViewList(context: Context, activity: FragmentActivity?): List<ImageView> {
        return helper.getImageViewList(context, activity)
    }

    fun startGame(images: List<ImageView>) {
        for (image in images) {
            image.setImageResource(R.drawable.ic_black_circle)
            clickableImages(images, false)
        }

        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                putRandomImages(images)
            }
        }.start()
    }

    private fun putRandomImages(images: List<ImageView>) {
        val juices = helper.getJuicyList()
        val poisons = helper.getPoisonList(juices)

        for (i in juices) {
            images[i].setImageResource(R.drawable.ic_juice)
        }

        for (i in poisons) {
            images[i].setImageResource(chooseRandomPoison())
        }
    }

    private fun chooseRandomPoison(): Int {
        return when ((0..1).random()) {
            0 -> R.drawable.ic_poison
            1 -> R.drawable.ic_wine
            else -> R.drawable.ic_poison
        }
    }

    private fun clickableImages(images: List<ImageView>, isClickable: Boolean) {
        for (image in images) {
            image.isClickable = isClickable
        }
    }
}