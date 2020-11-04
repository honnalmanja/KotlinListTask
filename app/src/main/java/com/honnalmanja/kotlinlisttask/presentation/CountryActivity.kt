package com.honnalmanja.kotlinlisttask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.honnalmanja.kotlinlisttask.R
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.databinding.ActivityCountryBinding
import com.honnalmanja.kotlinlisttask.di.Injector
import javax.inject.Inject

class CountryActivity : AppCompatActivity() {

    @Inject
    lateinit var countryViewModelFactory: CountryViewModelFactory

    private lateinit var viewModel: CountryViewModel

    private lateinit var binding: ActivityCountryBinding

    private lateinit var adapter: CountryDetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_country)
        (application as Injector).createCountrySubComponent().inject(this)
        viewModel = ViewModelProvider(this, countryViewModelFactory)
                .get(CountryViewModel::class.java)
        setSupportActionBar(binding.toolBar)

        binding.countryRv.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )
        getDetails()

        binding.countryListRefresh.setOnRefreshListener {
            viewModel.refreshCountry().observe(this, Observer {

                if(it != null){
                    updateAdapter(it)
                }else {
                    showErrorMessage("Something went wrong, Swipe to refresh data")
                }

            })
        }

    }

    private fun getDetails(){
        binding.countryListRefresh.isRefreshing = true
        viewModel.getCountry().observe(this , Observer { canada ->
            if(canada != null){
                updateAdapter(canada)
            } else {
                showErrorMessage("Something went wrong, Try Again")
            }

        })
    }

    private fun updateAdapter(canada: Canada?){
        binding.countryListRefresh.isRefreshing = false
        binding.toolBarTitleTv.text = canada?.title
        adapter = CountryDetailListAdapter()
        binding.countryRv.adapter = adapter
        adapter.setAboutCountry(canada!!.abouts)
        adapter.notifyDataSetChanged()
    }

    private fun showErrorMessage(message: String){
        binding.countryListRefresh.isRefreshing = false
        val snackBar: Snackbar = Snackbar.make(binding.countryHolderLlc, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("Reload", View.OnClickListener {
                getDetails()
                snackBar.dismiss()
            })
        snackBar.show()
    }
}