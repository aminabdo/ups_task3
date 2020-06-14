package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.home.ui.more.MoreFragment
import com.shoohna.happytimes.ui.home.ui.moreSetting.MoreSettingFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MoreSettingScreenTest {

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
        navController.navigate(R.id.moreSettingFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, MoreSettingFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    @Test
    fun notificationSwitchCheck(){
        onView(withId(R.id.notificationSwitchId)).perform(click())
    }
    @Test
    fun messageSwitchCheck(){
        onView(withId(R.id.messageSwitchId)).perform(click())
    }
    @Test
    fun spinnerClick(){
        onView(withId(R.id.spinner)).perform(click())
    }
    @Test
    fun spinnerCurrencyClick(){
        onView(withId(R.id.spinnerCurrency)).perform(click())
    }

}