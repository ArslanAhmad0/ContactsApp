package com.example.contactsapp.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.contactsapp.R
import org.junit.Before
import org.junit.Test

class SecondFragmentTest {

    @Before
    fun setup(){
        launchFragmentInContainer<SecondFragment>(themeResId = R.style.Theme_ContactsApp)
    }
    @Test
    fun recyclerView_should_show_RoomList(){

        Espresso.onView(ViewMatchers.withId(R.id.rv_Rooms_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<StaffListViewHolder>(64))
    }
    @Test
    fun buttonClickShouldDisplayRoomFragment(){
        Espresso.onView(ViewMatchers.withId(R.id.button_second))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

    }

}