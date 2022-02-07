package com.mmfsin.flashjuice

interface IListener {
    fun putNewHighScore(newHighScore: Int)
    fun showNewRecordFragment(level: Int)
    fun closeNewRecordFragment(goToRecordsFragment: Boolean)
    fun closeKeyboard()
}