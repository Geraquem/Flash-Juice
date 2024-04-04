package com.mmfsin.flashjuice

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1500)
        setTheme(R.style.Theme_FlashJuice)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun goToFragment(fragment: Fragment) {
//      supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, fragment)
//            .addToBackStack(null).commit()
    }

    private fun getLevelRecord(): Int {
//        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return 1
//        return sharedPref.getInt(getString(R.string.saved_high_score_level), 1)
        return 0
    }


    private fun closeKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
