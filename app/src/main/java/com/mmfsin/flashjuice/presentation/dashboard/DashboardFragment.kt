package com.mmfsin.flashjuice.presentation.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseFragment
import com.mmfsin.flashjuice.databinding.FragmentDashboardBinding
import com.mmfsin.flashjuice.domain.models.Difficult
import com.mmfsin.flashjuice.domain.models.Difficult.HARD
import com.mmfsin.flashjuice.domain.models.Difficult.NORMAL
import com.mmfsin.flashjuice.domain.models.GameEnd
import com.mmfsin.flashjuice.domain.models.GameEnd.LOOSER
import com.mmfsin.flashjuice.domain.models.GameEnd.WINNER
import com.mmfsin.flashjuice.domain.models.Positions
import com.mmfsin.flashjuice.domain.models.Tags
import com.mmfsin.flashjuice.domain.models.Tags.JUICE
import com.mmfsin.flashjuice.domain.models.Tags.POISON1
import com.mmfsin.flashjuice.domain.models.Tags.POISON2
import com.mmfsin.flashjuice.domain.models.Tags.POISON3
import com.mmfsin.flashjuice.domain.models.Tags.POISON4
import com.mmfsin.flashjuice.presentation.MainActivity
import com.mmfsin.flashjuice.presentation.dashboard.dialogs.GameEndDialog
import com.mmfsin.flashjuice.presentation.dashboard.listeners.IGameEndListener
import com.mmfsin.flashjuice.presentation.menu.MenuDialog
import com.mmfsin.flashjuice.utils.countDown
import com.mmfsin.flashjuice.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(),
    IGameEndListener {

    override val viewModel: DashboardViewModel by viewModels()
    private lateinit var mContext: Context

    private var difficult: Difficult = NORMAL

    private lateinit var images: List<ImageView>
    private var level = 1
    private var lifes = 5
    private var duration: Long = 1000
    private var juicesSuccess = 0

    private var poisonOne: Int = R.drawable.ic_poison_one
    private var poisonTwo: Int = R.drawable.ic_poison_two
    private var poisonThree: Int = R.drawable.ic_poison_three
    private var poisonFour: Int = R.drawable.ic_error

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
            if (diff == HARD) {
                poisonOne = R.drawable.ic_black_circle_trans
                poisonTwo = R.drawable.ic_black_circle_trans
                poisonThree = R.drawable.ic_black_circle_trans
                poisonFour = R.drawable.ic_black_circle_trans
            }
            countDown(10) { viewModel.getImages(binding.table) }
        }
        activity?.let { menuDialog.show(it.supportFragmentManager, "") }
    }

    override fun setUI() {
        binding.apply {
            setLevelText()
            setLifesText()
        }
    }

    private fun setLevelText() {
        binding.apply {
            tvLevel.text = getString(R.string.dashboard_level, level.toString())
        }
    }

    private fun setLifesText() {
        binding.apply {
            tvLifes.text = lifes.toString()
            val heart = if (lifes < 1) R.drawable.ic_heart_broken else R.drawable.ic_heart
            ivHeart.setImageResource(heart)
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is DashboardEvent.GetImages -> {
                    images = event.images
                    setImagesListeners()
                    viewModel.getPositions(level)
                }

                is DashboardEvent.GetPositions -> {
                    duration = event.positions.duration
                    startGame(event.positions)
                }

                is DashboardEvent.SWW -> error()
            }
        }
    }

    private fun startGame(positions: Positions) {
        binding.apply {
            try {
                setLevelText()
                areImagesClickable(enabled = false)
                setBlackImages()
                countDown(800) {
                    setPoisons(positions.poisons1, poisonOne, POISON1)
                    positions.poisons2?.let { setPoisons(it, poisonTwo, POISON2) }
                    positions.poisons3?.let { setPoisons(it, poisonThree, POISON3) }
                    positions.poisons4?.let { setPoisons(it, poisonFour, POISON4) }
                    setJuices(positions.juices)

                    /** hide again */
                    countDown(duration) {
                        setBlackImages()
                        countDown(100) { areImagesClickable(enabled = true) }
                    }
                }

            } catch (e: Exception) {
                error()
            }
        }
    }

    private fun setBlackImages() {
        images.forEach { it.setImageResource(R.drawable.ic_black_circle) }
    }

    private fun setJuices(juicesPositions: List<Int>) {
        binding.apply {
            for (i in juicesPositions) {
                images[i].setImageResource(R.drawable.ic_juice)
                images[i].tag = JUICE
            }
        }
    }

    private fun setPoisons(poisonsPositions: List<Int>, poisonImage: Int, tag: Tags) {
        binding.apply {
            for (i in poisonsPositions) {
                images[i].setImageResource(poisonImage)
                images[i].tag = tag
            }
        }
    }

    private fun areImagesClickable(enabled: Boolean) {
        images.forEach { image -> image.isEnabled = enabled }
    }

    private fun setImagesListeners() {
        images.forEach { image ->
            image.setOnClickListener {
                when (image.tag) {
                    JUICE -> {
                        image.setImageResource(R.drawable.ic_juice)
                        juicesSuccess++
                    }

                    POISON1 -> image.clickOnPoison(poisonOne)
                    POISON2 -> image.clickOnPoison(poisonTwo)
                    POISON3 -> image.clickOnPoison(poisonThree)
                    POISON4 -> image.clickOnPoison(poisonFour)
                    else -> R.drawable.ic_error
                }
                image.isEnabled = false
                checkIfEndGame()
            }
        }
    }

    private fun ImageView.clickOnPoison(poisonImage: Int) {
        this.setImageResource(poisonImage)
        lifes--
        setLifesText()
    }

    private fun checkIfEndGame() {
        if (juicesSuccess > 4) {
            areImagesClickable(enabled = false)
            showEndDialog(WINNER)
        }
        if (lifes < 1) {
            areImagesClickable(enabled = false)
            showEndDialog(LOOSER)
        }
    }

    private fun showEndDialog(result: GameEnd) {
        val menuDialog = GameEndDialog(result, this@DashboardFragment)
        activity?.let {
            countDown(200) { menuDialog.show(it.supportFragmentManager, "") }
        }
    }

    override fun nextLevel() {
        juicesSuccess = 0
        setLifesText()
        viewModel.getPositions(level++)
    }

    override fun restart() {
        level = 1
        lifes = 5
        juicesSuccess = 0
        setLifesText()
        viewModel.getPositions(level)
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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}