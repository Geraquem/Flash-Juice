package com.mmfsin.flashjuice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mmfsin.flashjuice.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.setOnClickListener {
            recordText.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DashboardFragment())
                .addToBackStack(null).commit()
        }
    }

    override fun onBackPressed() {
        recordText.visibility = View.VISIBLE
        super.onBackPressed()
    }
}