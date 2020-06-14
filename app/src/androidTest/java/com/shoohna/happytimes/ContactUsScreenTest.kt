package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.home.ui.contactUs.ContactUsFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ContactUsScreenTest {


    private lateinit var activity : MainActivity
    private lateinit var navController : TestNavHostController

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setUp() {
        navController = TestNavHostController(mActivityTestRule.activity!!)
        navController.setViewModelStore(ViewModelStore())

        navController.setGraph(R.navigation.nav_home)
        navController.navigate(R.id.contactUsFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, ContactUsFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    @Test
    fun contactUsClick(){
        onView(withId(R.id.emailEt)).perform(typeText("hebamuhammed@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.PhoneEt)).perform(typeText("01060945044"), closeSoftKeyboard())
        onView(withId(R.id.messageEt)).perform(typeText("Hello testing ..."), closeSoftKeyboard())
        onView(withId(R.id.button8)).perform(click())
    }
    @Test
    fun facebookIconClick()
    {
        onView(withId(R.id.facebookIconId)).perform(click())
    }
    @Test
    fun snapchatIconClick()
    {
        onView(withId(R.id.snapchatIconId)).perform(click())
    }
    @Test
    fun twitterIconClick()
    {
        onView(withId(R.id.twitterIconId)).perform(click())
    }
    @Test
    fun instagramIconClick()
    {
        onView(withId(R.id.instagramIconId)).perform(click())
    }
}