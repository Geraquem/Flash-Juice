package com.mmfsin.flashjuice.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.databinding.ActivityBedrockBinding
import com.mmfsin.flashjuice.utils.ROOT_ACTIVITY_NAV_GRAPH
import com.mmfsin.flashjuice.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BedRockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBedrockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBedrockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavGraph()
    }

    private fun setUpNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.br_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = intent.getIntExtra(ROOT_ACTIVITY_NAV_GRAPH, -1)
        navController.apply { if (navGraph != -1) setGraph(navGraph) else error() }
    }

    fun backListener(action: () -> Unit) {
        binding.ivBack.setOnClickListener { action() }
    }

    fun setToolbarText(title: Int) {
        binding.tvTitle.text = getString(title)
    }

    fun closeKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun error() = showErrorDialog() { finish() }
}