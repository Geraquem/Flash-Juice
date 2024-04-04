package com.mmfsin.flashjuice.zzzzzzdelete.view.dashboard.helper

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.mmfsin.flashjuice.R

class DashBoardHelper {

    fun getResultPhrases(context: Context, isGood: Boolean): List<String> {
        return if (isGood) {
            context.resources.getStringArray(R.array.goodPhrases).toList()
        } else {
            context.resources.getStringArray(R.array.badPhrases).toList()
        }
    }

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

    fun clickableImages(images: List<ImageView>, isClickable: Boolean) {
        for (image in images) {
            image.isClickable = isClickable
        }
    }

    fun getDisappearedTime(level: Int): Long {
        return when {
            level == 1 -> 1000
            level < 3 -> 900
            level < 5 -> 700
            level < 7 -> 600
            level < 9 -> 500
            level < 12 -> 400
            level < 16 -> 300
            level < 18 -> 350
            level < 20 -> 200
            level < 22 -> 170
            level < 24 -> 165
            level < 26 -> 160
            else -> 155
        }
    }
}