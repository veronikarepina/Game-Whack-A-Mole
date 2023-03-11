package ru.veronikarepina.whac_a_mole.ui.firstscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.veronikarepina.whac_a_mole.R
import ru.veronikarepina.whac_a_mole.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private val viewModel: FirstViewModel by viewModels()
    private lateinit var binding: FragmentFirstBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.highScore.observe(viewLifecycleOwner){
            binding.highScore.text = it?.toString() ?: getString(R.string.emptyScore)
        }
        binding.buttonPlay.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}