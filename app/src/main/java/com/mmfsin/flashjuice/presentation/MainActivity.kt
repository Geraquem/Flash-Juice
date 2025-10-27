package com.mmfsin.flashjuice.presentation

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BedRockActivity
import com.mmfsin.flashjuice.databinding.ActivityMainBinding
import com.mmfsin.flashjuice.utils.ARGS_RECORD
import com.mmfsin.flashjuice.utils.ROOT_ACTIVITY_NAV_GRAPH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mInterstitialAd: InterstitialAd? = null

    var firstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeStatusBarColor(R.color.white)
        setAds()
        setAnimateBackground()
    }

    private fun changeStatusBarColor(color: Int) {
        // Android 15+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
                view.setBackgroundColor(ContextCompat.getColor(this, color))
                view.setPadding(0, statusBarInsets.top, 0, 0)
                insets
            }

        } else {
            // For Android 14 and below
            @Suppress("DEPRECATION") window.statusBarColor = ContextCompat.getColor(this, color)
        }

        //true == dark
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
    }

    private fun setAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        loadInterstitial(AdRequest.Builder().build())
    }

    private fun setAnimateBackground() {
        val animationDrawable = binding.ivBackground.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    fun openRanking(args: Long? = null) {
        val intent = Intent(this, BedRockActivity::class.java)
        args?.let { intent.putExtra(ARGS_RECORD, args) }
        intent.putExtra(ROOT_ACTIVITY_NAV_GRAPH, R.navigation.nav_graph_ranking)
        startActivity(intent)
    }

    fun openInstructions() {
        val intent = Intent(this, BedRockActivity::class.java)
        intent.putExtra(ROOT_ACTIVITY_NAV_GRAPH, R.navigation.nav_graph_instructions)
        startActivity(intent)
    }

    private fun loadInterstitial(adRequest: AdRequest) {
//        InterstitialAd.load(this,
//            getString(R.string.insterstitial),
//            adRequest,
//            object : InterstitialAdLoadCallback() {
//                override fun onAdFailedToLoad(adError: LoadAdError) {
//                    mInterstitialAd = null
//                    loadInterstitial(AdRequest.Builder().build())
//                }
//
//                override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                    mInterstitialAd = interstitialAd
//                }
//            })
    }
}
