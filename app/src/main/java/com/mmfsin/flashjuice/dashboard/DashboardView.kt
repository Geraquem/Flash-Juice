package com.mmfsin.flashjuice.dashboard

interface DashboardView {

    fun putBlackCircles()
    fun putJuices(juices: List<Int>)
    fun putPoisonsFirstPhase(poisons: List<Int>)
    fun putPoisonsSecondPhase(poisons: List<Int>)

    fun setImageViewListeners()

    fun updateLevel()
    fun updateLifes()

    fun showGoodResult(view: Int)
    fun showBadResult(view: Int)
}