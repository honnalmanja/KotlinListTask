package com.honnalmanja.kotlinlisttask.presentation

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.data.repository.FakeCountryRepository
import com.honnalmanja.kotlinlisttask.domain.CountryUseCase
import com.honnalmanja.kotlinlisttask.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class CountryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CountryViewModel

    @Before
    fun setUp() {
        val repository = FakeCountryRepository()
        val countryUseCase = CountryUseCase(repository)
        viewModel = CountryViewModel(countryUseCase)
    }

    @Test
    fun getCountry_currentCountry() {
        val aboutList: List<About> = listOf(
            About("Description 1","Image 1","Title 1"),
            About("Description 2","Image 2","Title 2"),
            About("Description 3","Image 3","Title 3"),

        )
        val canada = Canada(aboutList, "Canada")
        val oldCanada = viewModel.getCountry().getOrAwaitValue()
        Truth.assertThat(canada).isEqualTo(oldCanada)
    }

    @Test
    fun refreshCountry_updatedCountry() {
        val aboutList: List<About> = listOf(
            About("Description 4","Image 4","Title 4"),
            About("Description 5","Image 5","Title 5")
        )


        val canada = Canada(aboutList, "Canada")
        val oldCanada = viewModel.refreshCountry().getOrAwaitValue()
        Truth.assertThat(canada).isEqualTo(oldCanada)
    }

}