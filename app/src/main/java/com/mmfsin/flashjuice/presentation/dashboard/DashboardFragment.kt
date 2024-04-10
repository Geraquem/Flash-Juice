package com.mmfsin.flashjuice.presentation.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseFragment
import com.mmfsin.flashjuice.databinding.FragmentDashboardBinding
import com.mmfsin.flashjuice.domain.models.Positions
import com.mmfsin.flashjuice.domain.models.Tags
import com.mmfsin.flashjuice.domain.models.Tags.JUICE
import com.mmfsin.flashjuice.domain.models.Tags.POISON1
import com.mmfsin.flashjuice.domain.models.Tags.POISON2
import com.mmfsin.flashjuice.domain.models.Tags.POISON3
import com.mmfsin.flashjuice.domain.models.Tags.POISON4
import com.mmfsin.flashjuice.presentation.MainActivity
import com.mmfsin.flashjuice.presentation.dashboard.dialogs.EndGameDialog
import com.mmfsin.flashjuice.presentation.dashboard.dialogs.GoodLevelDialog
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

    private lateinit var images: List<ImageView>
    private var level: Long = 4
    private var lifes = 50
    private var duration: Long = 1000
    private var juicesSuccess = -21

    private var dialogGood: GoodLevelDialog? = null
    private var dialogEndGame: EndGameDialog? = null

    private var poisonOne: Int = R.drawable.ic_poison_one
    private var poisonTwo: Int = R.drawable.ic_poison_two
    private var poisonThree: Int = R.drawable.ic_poison_three
    private var poisonFour: Int = R.drawable.ic_poison_four

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countDown(200) { showMenuDialog() }
    }

    private fun showMenuDialog() {
        val menuDialog = MenuDialog {
            (activity as MainActivity).apply {
                if (this.firstTime) {
                    this.firstTime = false
                    viewModel.getImages(binding.table)
                } else restart()
            }
        }
        activity?.let { menuDialog.show(it.supportFragmentManager, "") }
    }

    override fun setUI() {
        binding.apply {
            setLevelText()
            restartLifes()
        }
    }

    override fun setListeners() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            showMenuDialog()
        }
    }

    private fun setLevelText() {
        binding.apply {
            tvLevel.text = getString(R.string.dashboard_level, level.toString())
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
        images.forEach { it.setImageResource(R.drawable.ic_small_dot) }
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
        lifeLost()
    }

    private fun checkIfEndGame() {
        if (juicesSuccess > 4) {
            areImagesClickable(enabled = false)
            showGoodLevelDialog()
        }
        if (lifes < 1) {
            areImagesClickable(enabled = false)
            showEndGameDialog()
        }
    }

    private fun showGoodLevelDialog() {
        dialogGood = GoodLevelDialog(level, this@DashboardFragment)
        activity?.let {
            countDown(200) { dialogGood?.show(it.supportFragmentManager, "") }
        }
    }

    private fun showEndGameDialog() {
        dialogEndGame = EndGameDialog(level, juicesSuccess, this@DashboardFragment)
        activity?.let {
            countDown(200) { dialogEndGame?.show(it.supportFragmentManager, "") }
        }
    }

    override fun nextLevel() {
        juicesSuccess = 0
        viewModel.getPositions(level++)
    }

    override fun restart() {
        level = 1
        lifes = 5
        juicesSuccess = 0
        restartLifes()
        viewModel.getPositions(level)
    }

    private fun lifeLost() {
        binding.lifes.apply {
            when (lifes) {
                4 -> life5.setImageResource(R.drawable.ic_cross)
                3 -> life4.setImageResource(R.drawable.ic_cross)
                2 -> life3.setImageResource(R.drawable.ic_cross)
                1 -> life2.setImageResource(R.drawable.ic_cross)
                0 -> life1.setImageResource(R.drawable.ic_cross)
                else -> {}
            }
        }
    }

    private fun restartLifes() {
        binding.lifes.apply {
            life1.setImageResource(R.drawable.ic_heart)
            life2.setImageResource(R.drawable.ic_heart)
            life3.setImageResource(R.drawable.ic_heart)
            life4.setImageResource(R.drawable.ic_heart)
            life5.setImageResource(R.drawable.ic_heart)
        }
    }

    override fun setNewWorldRecord() {
        (activity as MainActivity).openRanking(args = level)
        countDown(300) { showMenuDialog() }
    }

    private fun error() =
        activity?.let { it.showErrorDialog { it.onBackPressedDispatcher.onBackPressed() } }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}