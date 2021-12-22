package com.mmfsin.flashjuice.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.mmfsin.flashjuice.R

interface DashboardView {

    fun putBlackCircles()
    fun putJuices()
    fun putPoisons()

    fun updateLevel()
    fun updateLifes()
}