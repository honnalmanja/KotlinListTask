package com.honnalmanja.kotlinlisttask.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.honnalmanja.kotlinlisttask.R
import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.databinding.CountryRowItemBinding

class CountryDetailListAdapter() : RecyclerView.Adapter<CountryDetailViewHolder>() {

    private var aboutList = ArrayList<About>()

    fun setAboutCountry(aboutList: List<About>){
        this.aboutList.clear()
        this.aboutList.addAll(aboutList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailViewHolder {
       val binding: CountryRowItemBinding = DataBindingUtil.inflate(
               LayoutInflater.from(parent.context),
               R.layout.country_row_item, parent,false
       )
        return CountryDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryDetailViewHolder, position: Int) {
        holder.bindValues(aboutList[position])
    }

    override fun getItemCount(): Int = aboutList.size
}