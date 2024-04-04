package com.mmfsin.flashjuice.presentation.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.base.BaseFragment
import com.mmfsin.flashjuice.databinding.FragmentDashboardBinding
import com.mmfsin.flashjuice.domain.models.Difficult
import com.mmfsin.flashjuice.domain.models.Difficult.NORMAL
import com.mmfsin.flashjuice.presentation.MainActivity
import com.mmfsin.flashjuice.presentation.menu.MenuDialog
import com.mmfsin.flashjuice.utils.countDown
import com.mmfsin.flashjuice.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    //    private val juiceTAG = "JUICE"
//    private val poison1TAG = "POISON1"
//    private val poison2TAG = "POISON2"
//    private val poison3TAG = "POISON3"
//
//    //    private val presenter by lazy { DashboardPresenter(this) }
//    private lateinit var images: List<ImageView>
//    private lateinit var endJuices: List<ImageView>
//
//    private lateinit var goodPhrases: List<String>
//    private lateinit var badPhrases: List<String>
//
//    private var level = 1
//    private var lifes = 5
//    private var numJuices = 0


    override val viewModel: DashboardViewModel by viewModels()
    private lateinit var mContext: Context

    private var difficult: Difficult = NORMAL

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countDown(750) {
            showMenuDialog()
//            showBanner()
        }
    }

    private fun showMenuDialog() {
        val menuDialog = MenuDialog { diff ->
            difficult = diff
            countDown(1000) { startGame() }
        }
        activity?.let { menuDialog.show(it.supportFragmentManager, "") }
    }

    override fun setUI() {
        binding.apply { }
    }

    private fun startGame() {
        viewModel.getJuices()
    }

    override fun setListeners() {
        binding.apply { }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is DashboardEvent.ImageHeight -> {}
                is DashboardEvent.SomethingWentWrong -> error()
            }
        }
    }

    private fun showBanner() {
        try {
            (activity as MainActivity).bannerVisibility(true)
        } catch (e: Exception) {
            Log.e("BANNER", "Error showing banner")
        }
    }

    private fun error() =
        activity?.let { it.showErrorDialog { it.onBackPressedDispatcher.onBackPressed() } }


//        goodPhrases = presenter.getResultPhrases(mContext, true)
//        badPhrases = presenter.getResultPhrases(mContext, false)
//        endJuices = presenter.getJuicesImageViewList(mContext, activity)
//
//        images = presenter.getImageViewList(mContext, activity)
//        presenter.startGame(images, level)

//        goodResult.nextLevel.setOnClickListener {
//            showGoodResult(View.GONE)
//            numJuices = 0
//            level++
//            presenter.startGame(images, level)
//        }
//
//        badResult.retryButton.setOnClickListener {
//            showBadResult(View.GONE)
//            numJuices = 0
//            level = 1
//            lifes = 5
//            presenter.startGame(images, level)
//        }
//}
//
//    override fun updateLevel() {
////        levelText.text = getString(R.string.level, level.toString())
//    }
//
//    override fun updateLifes() {
////        lifesText.text = lifes.toString()
//    }
//
//    override fun putBlackCircles() {
//        for (image in images) {
//            image.setImageResource(R.drawable.ic_black_circle)
//        }
//    }
//
//    override fun putJuices(juices: List<Int>) {
//        for (i in juices) {
//            images[i].setImageResource(R.drawable.ic_juice)
//            images[i].tag = juiceTAG
//        }
//    }
//
//    override fun putPoisons(poisons: List<Int>, phase: Int) {
//        for (i in poisons) {
//            when ((0..phase).random()) {
//                0 -> {
//                    images[i].setImageResource(R.drawable.ic_poison_one)
//                    images[i].tag = poison1TAG
//                }
//
//                1 -> {
//                    images[i].setImageResource(R.drawable.ic_poison_two)
//                    images[i].tag = poison2TAG
//                }
//
//                2 -> {
//                    images[i].setImageResource(R.drawable.ic_poison_three)
//                    images[i].tag = poison3TAG
//                }
//            }
//        }
//    }
//
//    override fun setImageViewListeners() {
//        for (image in images) {
//            image.setOnClickListener {
//                if (image.tag == juiceTAG) {
//                    imageOnClick(image, true)
//                } else {
//                    imageOnClick(image, false)
//                }
//            }
//        }
//    }

//    private fun imageOnClick(image: ImageView, isGoodClick: Boolean) {
//        if (isGoodClick) {
//            numJuices++
//            image.setImageResource(R.drawable.ic_juice)
//        } else {
//            lifes--
//            when (image.tag) {
//                poison1TAG -> image.setImageResource(R.drawable.ic_poison_one)
//                poison2TAG -> image.setImageResource(R.drawable.ic_poison_two)
//                else -> image.setImageResource(R.drawable.ic_poison_three)
//            }
//        }
//        image.isClickable = false
//        presenter.updateUI(lifes, numJuices, level)
//    }
//
//    override fun showGoodResult(view: Int) {
//        goodResultText.text = presenter.setPhrase(goodPhrases)
//        goodResultLevelText.text = getString(R.string.levelCompleted, level.toString())
//        goodResult.visibility = view
//        lottieGoodResult.playAnimation()
//    }
//
//    override fun showBadResult(view: Int) {
//        badResultText.text = presenter.setPhrase(badPhrases)
//        badResultLevelText.text = getString(R.string.levelFailed, level.toString())
//        presenter.setJuiceErrors(endJuices, numJuices)
//        badResult.visibility = view
//        lottieBadResult.playAnimation()
//    }
//
//    override fun checkHighScore(isEndGame: Boolean) {
//        if (level > savedHighScore) {
//            listener.putNewHighScore(level)
//            if (isEndGame) {
//                Toast.makeText(mContext, getString(R.string.newRecord), Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    override fun showNewRecordFragment(level: Int) {
//        listener.showNewRecordFragment(level)
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}