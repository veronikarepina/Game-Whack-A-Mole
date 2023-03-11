package ru.veronikarepina.whac_a_mole.ui.secondscreen

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.os.bundleOf
import androidx.core.view.setMargins
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.veronikarepina.whac_a_mole.R
import ru.veronikarepina.whac_a_mole.databinding.FragmentSecondBinding
import ru.veronikarepina.whac_a_mole.model.Scores
import kotlin.random.Random

class SecondFragment : Fragment() {
    private val viewModel: SecondViewModel by viewModels()
    private lateinit var binding: FragmentSecondBinding
    private lateinit var timer: CountDownTimer
    private lateinit var tableLayout: TableLayout
    private var score = 0
    private var hScore = 0
    private val arrayImages: Array<Array<FrameLayout?>> = Array(COUNT_COLUMNS) { arrayOfNulls(COUNT_ROWS) }

    companion object{
        const val COUNT_ROWS = 3
        const val COUNT_COLUMNS = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.highScore.observe(viewLifecycleOwner){
            binding.hsCountSecond.text = it?.toString() ?: getString(R.string.emptyScore)
            hScore = it ?: 0
        }
        tableLayout = binding.tableLayout

        timer = object : CountDownTimer(30000, 500) {
            override fun onTick(p0: Long) {
                setGameView(p0)
            }

            override fun onFinish() {
                val bundle = bundleOf("last_score" to score)
                viewModel.insertScore(Scores(score))
                findNavController().navigate(R.id.action_secondFragment_to_thirdFragment, bundle)
            }
        }.start()
    }

    private fun setGameView(timeToFinish: Long){
        binding.time.text = (timeToFinish / 1000).toString()
        val randomRow = Random.nextInt(COUNT_ROWS)
        val randomColumn = Random.nextInt(COUNT_COLUMNS)
        for(row in 0 until COUNT_ROWS){
            for(column in 0 until COUNT_COLUMNS){
                val frameLayout = FrameLayout(requireContext())
                val imageView = ImageView(requireContext())
                val params = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0F
                )
                params.setMargins(10)
                frameLayout.layoutParams = params
                frameLayout.setBackgroundResource(R.drawable.icon_hole)

                if (row == randomRow && column == randomColumn) {
                    imageView.setImageResource(R.drawable.icon_mole)
                    imageView.setOnClickListener {
                        score++
                        binding.scoreCountSecond.text = score.toString()
                        if(score > hScore) setHighScoreText(score)
                        imageView.setImageResource(R.drawable.icon_whack)
                        imageView.isEnabled = false
                    }
                }
                frameLayout.addView(imageView)
                arrayImages[row][column] = frameLayout
            }
        }
        tableLayout.removeAllViews()
        for (row in 0 until COUNT_ROWS) {
            val tableRow = TableRow(requireContext())
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
            )
            tableRow.gravity = Gravity.CENTER
            for (column in 0 until COUNT_COLUMNS) {
                tableRow.addView(arrayImages[row][column], column)
            }

            tableLayout.addView(tableRow, row)
        }
    }

    private fun setHighScoreText(score: Int){
        binding.hsCountSecond.text = score.toString()
    }
}