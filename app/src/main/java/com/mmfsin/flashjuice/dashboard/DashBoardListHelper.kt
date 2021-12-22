package com.mmfsin.flashjuice.dashboard

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity

class DashBoardListHelper {

    fun getImageViewList(context: Context, activity: FragmentActivity?): List<ImageView> {
        val images = mutableListOf<ImageView>()
        for (i in 1..20) {
            val name = "i$i"
            val res = context.resources.getIdentifier(name, "id", context.packageName)
            images.add(activity!!.findViewById(res))
        }
        return images
    }

    fun getJuicyList(): List<Int> {
        val list = linkedSetOf<Int>()
        while (list.size < 5) {
            list.add((0..19).random())
        }
        return ArrayList<Int>(list)
    }

    fun getPoisonList(juices: List<Int>): List<Int> {
        return mutableListOf<Int>().apply {
            for (i in (0..19)) {
                if (!juices.contains(i)) {
                    add(i)
                }
            }
        }
    }
}