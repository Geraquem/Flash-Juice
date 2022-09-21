package com.mmfsin.flashjuice

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.mmfsin.flashjuice.view.dashboard.DashboardFragment
import com.mmfsin.flashjuice.view.newrecord.NewRecordFragment
import com.mmfsin.flashjuice.view.ranking.RankingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1500)
        setTheme(R.style.Theme_FlashJuice)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val savedHighScore = getLevelRecord()
        recordText.text = getString(R.string.record, savedHighScore.toString())

        ranking.setOnClickListener {
            ll_main.visibility = View.GONE
            goToFragment(RankingFragment(this))
        }

        playButton.setOnClickListener { goToFragment(DashboardFragment(this, savedHighScore)) }
    }

    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
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
        if (goToRecordsFragment) {
            goToFragment(RankingFragment(this))
        }
    }

    override fun closeKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun somethingWentWrong() {
        Toast.makeText(this, getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ll_main.visibility = View.VISIBLE
    }
}
