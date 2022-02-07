package com.mmfsin.flashjuice.view.dashboard

interface DashboardView {

    fun putBlackCircles()
    fun putJuices(juices: List<Int>)
    fun putPoisons(poisons: List<Int>, phase: Int)

    fun setImageViewListeners()

    fun updateLevel()
    fun updateLifes()

    fun showGoodResult(view: Int)
    fun showBadResult(view: Int)

    fun checkHighScore(isEndGame: Boolean)
    fun showNewRecordFragment(level: Int)
}