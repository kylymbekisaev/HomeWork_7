package com.example.homework_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_7.databinding.FragmentC2Binding

class Fragment_C : Fragment() {

    private var _binding: FragmentC2Binding? = null
    private val binding: FragmentC2Binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentC2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnButton.setOnClickListener {
            val imageEt = edAnime.text.toString().trim()
            val textEt = edNaruto.text.toString().trim()

            val imageBundle = Bundle()
            val imageModel = Image(imageEt, listOf(textEt))
            imageBundle.putSerializable("imageModel", imageModel)

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, Fragment_A().apply {
                    arguments = imageBundle
                })
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}