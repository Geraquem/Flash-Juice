package com.mmfsin.flashjuice.presentation

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.mmfsin.flashjuice.base.BedRockActivity
import com.mmfsin.flashjuice.databinding.ActivityMainBinding
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

        setAds()
        setAnimateBackground()
    }

    private fun setAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        bannerVisibility(visible = false)
        loadInterstitial(AdRequest.Builder().build())
    }

    private fun setAnimateBackground() {
        val animationDrawable = binding.ivBackground.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    fun closeKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun openActivity(navGraph: Int, argsName: String? = null, args: String? = null) {
        val intent = Intent(this, BedRockActivity::class.java)
        args?.let { intent.putExtra(argsName, args) }
        intent.putExtra(ROOT_ACTIVITY_NAV_GRAPH, navGraph)
        startActivity(intent)
    }

    fun bannerVisibility(visible: Boolean) {
        val view = if (visible) View.VISIBLE else View.GONE
        binding.flBanner.visibility = view
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
