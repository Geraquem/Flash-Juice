package com.mmfsin.flashjuice.dashboard

interface DashboardView {

    fun putBlackCircles()
    fun putJuices(juices: List<Int>)
    fun putPoisons(poisons: List<Int>, phase: Int)

    fun setImageViewListeners()

    fun updateLevel()
    fun updateLifes()

    fun showGoodResult(view: Int)
    fun showBadResult(view: Int)

    fun checkHighScore(isGameEnd: Boolean)
}