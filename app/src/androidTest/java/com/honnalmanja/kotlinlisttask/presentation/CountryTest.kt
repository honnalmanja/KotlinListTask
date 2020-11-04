package com.honnalmanja.kotlinlisttask.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.honnalmanja.kotlinlisttask.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class CountryTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<CountryActivity>
            = ActivityScenarioRule(CountryActivity::class.java)


    @Test
    fun initialTitleTest() {
        onView(withId(R.id.tool_bar_title_tv))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.country_rv))
                .check(matches(ViewMatchers.isDisplayed()))

    }

    // check this before making network call
    @Test
    fun checkForTitleDisplay() {
        onView(withId(R.id.tool_bar_title_tv))
                .check(matches(withText("KotlinListTask")))
    }

    @Test
    fun checkForNetworkTitleDisplay() {
        onView(withId(R.id.tool_bar_title_tv))
                .check(matches(withText("About Canada")))
    }

    @Test
    fun recyclerViewItemCountTest() {
        this.activityRule.scenario.onActivity { activity->
            val recyclerView: RecyclerView = activity.findViewById(R.id.country_rv)
            val itemCount = recyclerView.adapter?.itemCount
            // check recyclerview getting item_rows
            if (itemCount != null) {
                assert(itemCount > 0)
            }
        }
    }

    @Test
    fun recyclerViewScrollToLastTest() {

//        onView(withId(R.id.country_rv))
//                .perform(actionOnItemAtPosition<CountryDetailViewHolder>(
//                        1, click()
//                ))
        var itemCount: Int? = 0
        this.activityRule.scenario.onActivity { activity->
            val recyclerView: RecyclerView = activity.findViewById(R.id.country_rv)
            val count = recyclerView.adapter?.itemCount
            // check recyclerview getting item_rows
            if (itemCount != null) {
                itemCount = count
            }
        }

        if(itemCount!! >0){
            onView(withId(R.id.country_rv))
                    .perform(RecyclerViewActions
                            .scrollToPosition<CountryDetailViewHolder>((itemCount!! - 1))
                    )
        }


    }



}