package com.mmfsin.flashjuice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mmfsin.flashjuice.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val savedHighScore = getLevelRecord()
        recordText.text = getString(R.string.record, savedHighScore.toString())

        playButton.setOnClickListener {
            recordText.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DashboardFragment(this, savedHighScore))
                .addToBackStack(null).commit()
        }
    }

    private fun getLevelRecord(): Int {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return 1
        return sharedPref.getInt(getString(R.string.saved_high_score_level), 1)
    }


    override fun onBackPressed() {
        recordText.visibility = View.VISIBLE
        super.onBackPressed()
    }

    override fun putNewHighScore(newHighScore: Int) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(getString(R.string.saved_high_score_level), newHighScore)
            apply()
        }
        recordText.text = getString(R.string.record, newHighScore.toString())
    }
}
