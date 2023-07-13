package com.example.memepicturesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memepicturesapp.databinding.RecviewBinding
import com.example.memepicturesapp.models.Meme
import com.example.memepicturesapp.models.MemeList
import com.squareup.picasso.Picasso

class Rwadapter(private val memeList: List<Meme>):RecyclerView.Adapter<Rwadapter.ViewHolder>() {

    class ViewHolder(val binding:RecviewBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return memeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem=memeList[position]
        holder.binding.apply {
            textView.text=currentItem.name
            Picasso.get().load(currentItem.url).into(imageview)
        }

    }

}