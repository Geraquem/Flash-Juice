package com.mmfsin.flashjuice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mmfsin.flashjuice.view.dashboard.DashboardFragment
import com.mmfsin.flashjuice.view.newrecord.NewRecordFragment
import com.mmfsin.flashjuice.view.ranking.RankingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val savedHighScore = getLevelRecord()
        recordText.text = getString(R.string.record, savedHighScore.toString())

        rankingButton.setOnClickListener { goToFragment(RankingFragment()) }
        playButton.setOnClickListener { goToFragment(DashboardFragment(this, savedHighScore)) }
    }

    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }

    private fun getLevelRecord(): Int {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return 1
        return sharedPref.getInt(getString(R.string.saved_high_score_level), 1)
    }

    override fun putNewHighScore(newHighScore: Int) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(getString(R.string.saved_high_score_level), newHighScore)
            apply()
        }
        recordText.text = getString(R.string.record, newHighScore.toString())
    }

    override fun showNewRecordFragment(level: Int) {
        supportFragmentManager.popBackStack()
        goToFragment(NewRecordFragment(this, level))
    }

    override fun closeNewRecordFragment(goToRecordsFragment: Boolean) {
        supportFragmentManager.popBackStack()
        if(goToRecordsFragment){
            goToFragment(RankingFragment())
        }
    }
}
