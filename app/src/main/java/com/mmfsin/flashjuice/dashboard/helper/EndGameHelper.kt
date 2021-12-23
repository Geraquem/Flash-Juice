package com.mmfsin.flashjuice.dashboard.helper

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.mmfsin.flashjuice.R

class EndGameHelper {

    fun getFiveJuices(context: Context, activity: FragmentActivity?): List<ImageView> {
        val juices = mutableListOf<ImageView>()
        for (i in 1..5) {
            val name = "juice$i"
            val res = context.resources.getIdentifier(name, "id", context.packageName)
            juices.add(activity!!.findViewById(res))
        }
        return juices
    }

    fun setJuiceErrors(endJuices: List<ImageView>, numJuices: Int) {
        if (numJuices == 0) {
            for (juice in endJuices) {
                juice.setImageResource(R.drawable.ic_cross)
            }
        }

        for (i in 0..numJuices) {
            endJuices[i].setImageResource(R.drawable.ic_juice)
        }

        for (i in numJuices until endJuices.size) {
            endJuices[i].setImageResource(R.drawable.ic_cross)

        }
    }
}