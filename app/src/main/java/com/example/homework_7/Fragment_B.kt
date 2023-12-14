package com.example.homework_7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.homework_7.R
import com.example.homework_7.databinding.FragmentBBinding

class Fragment_B : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding: FragmentBBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        close()
    }

    private fun getData() = with(binding){
        arguments?.let {
            val image = it.getSerializable(Fragment_A.IMAGE_KEY) as? Image
            if (image != null){
                Glide.with(ivImage.context)
                    .load(image.imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(ivImage)
                tvTage.text = image.tags.toString().replace("[","").replace("]","")
            }
        }
    }

    private fun close() {
        binding.tvTage.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}