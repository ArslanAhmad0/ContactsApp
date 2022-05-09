package com.example.contactsapp.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.contactsapp.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    @Before
    fun setup(){
        launchFragmentInContainer<FirstFragment>(themeResId = R.style.Theme_ContactsApp)
    }
    @Test
    fun recyclerView_should_show_Staff(){

        onView(withId(R.id.rv_staff_list))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<StaffListViewHolder>(66))
    }
    @Test
    fun recyclerViewShouldShowStaff(){
        onView(withId(R.id.rv_staff_list))
            .check(matches(isDisplayed()))
    }


    @Test
    fun buttonClickShouldDisplayRoomFragment(){
        onView(withId(R.id.button_first))
            .check(matches(isDisplayed()))
            .check(matches(withText("Room List")))
    }

}