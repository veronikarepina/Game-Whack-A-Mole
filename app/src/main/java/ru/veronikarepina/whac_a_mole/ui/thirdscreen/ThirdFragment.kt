package ru.veronikarepina.whac_a_mole.ui.thirdscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.veronikarepina.whac_a_mole.R
import ru.veronikarepina.whac_a_mole.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private val viewModel: ThirdViewModel by viewModels()
    private lateinit var binding: FragmentThirdBinding
    private val args: ThirdFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scoreCountThird.text = args.lastScore.toString()
        viewModel.highScore.observe(viewLifecycleOwner){
            binding.hsCountThird.text = it?.toString() ?: getString(R.string.emptyScore)
        }

        binding.buttonPlayAgain.setOnClickListener{
            findNavController().navigate(R.id.action_thirdFragment_to_secondFragment)
        }
        binding.buttonMenu.setOnClickListener{
            findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
        }
    }
}