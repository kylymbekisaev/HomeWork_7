package com.example.homework_7

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_7.R
import com.example.homework_7.databinding.FragmentABinding

class Fragment_A : Fragment(), OnItemClickListener {
    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding
        get() = _binding!!
    private val imageList = mutableListOf<Image>()
    private val AAdapter = AAdapter(this)


    override fun onItemClick(image: Image) {
        val bundle = Bundle().apply {
            putSerializable(IMAGE_KEY,image)
        }
        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, Fragment_B::class.java,bundle)
            .addToBackStack(Fragment_A::class.simpleName)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fillImages()
        scrollDawn()
        fragmentCCloss()
    }

    private fun setupRecyclerView() {
        binding.rvImage.adapter = AAdapter
    }

    private fun fillImages() {
        imageList.apply {
            repeat(100) {
                add(
                    Image(
                        imageUrl = "https://c4.wallpaperflare.com/wallpaper/764/505/66/baby-groot-4k-hd-superheroes-wallpaper-preview.jpg",
                        tags = listOf("HD wallpaper: baby groot","4k","hd","superheroes")
                    )
                )
            }
        }
        AAdapter.setImages(imageList)
    }

    private fun fragmentCCloss() {
        binding.btnButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container,Fragment_C::class.java,null)
                .addToBackStack(IMAGE_KEY)
                .commit()
        }
    }

    private fun scrollDawn() {
        binding.bottomFb.setOnClickListener{
            binding.rvImage.layoutManager?.let {
                it.scrollToPosition(AAdapter.itemCount -1)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val IMAGE_KEY = "image"
    }
}