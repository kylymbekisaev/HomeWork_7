package com.example.homework_7

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_7.databinding.ItemBinding

class AAdapter(private val onItemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<AAdapter.AViewHolder>() {

    private var imageList = mutableListOf<Image>()

    fun setImages(imageList: MutableList<Image>){
        this.imageList = imageList
        notifyDataSetChanged()
    }



    fun setImage(image: Image){
        imageList.add(image)
        notifyDataSetChanged()
    }

    fun deleteImage(index: Int) {
        imageList.removeAt(index)
        notifyDataSetChanged()
    }

    fun  updateImage(index: Int, image: Image){
        imageList[index] = image
        notifyDataSetChanged()
    }

    inner class AViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(
        binding.root){

        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(imageList[adapterPosition])
            }
        }

        fun onBind(image: Image) = with(binding){
            Glide.with(ivImage.context)
                .load(image.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(ivImage)

            tvTage.text = image.tags.toString().replace("[","").replace("]","")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AViewHolder {
       val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AViewHolder, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount() = imageList.size

}
