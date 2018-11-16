package com.dev.adi.collectapps

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.SearchView
import com.dev.adi.collectapps.sorting.SortingActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SortAutomate {
    @Rule
    @JvmField
    val rule = ActivityTestRule(SortingActivity::class.java)

    @Before
    fun setUp() {

    }

    @Test
    fun input_text()  {
        Espresso.onView(ViewMatchers.withId(R.id.action_search)).perform(click()).check { view, noViewFoundException ->
            (view as SearchView).setQuery("lol", true)
        }
//        Espresso.onView(ViewMatchers.withId(R.id.action_search)).perform(click())
        Thread.sleep(2000)
    }
}