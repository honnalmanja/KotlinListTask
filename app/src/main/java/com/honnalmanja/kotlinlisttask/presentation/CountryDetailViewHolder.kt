package com.honnalmanja.kotlinlisttask.presentation

import android.os.Build
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.databinding.CountryRowItemBinding
import com.honnalmanja.kotlinlisttask.utils.LogUtils
import com.honnalmanja.kotlinlisttask.utils.getProgressDrawable
import com.honnalmanja.kotlinlisttask.utils.loadImage


class CountryDetailViewHolder(private val binding: CountryRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    private val progressDrawable = getProgressDrawable(binding.root.context)

    fun bindValues(about: About){

        LogUtils.logD("About", "${about.title}")
        binding.countryDetailTitleTv.text = about.title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.countryDetailDescriptionTv.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
        binding.countryDetailDescriptionTv.text = about.description

        val posterURL = about.imageHref
        if(posterURL != null){
            binding.countryDetailImageIv.loadImage(posterURL, progressDrawable)
        } else {
            binding.countryDetailImageIv.visibility = View.GONE
        }

    }

}