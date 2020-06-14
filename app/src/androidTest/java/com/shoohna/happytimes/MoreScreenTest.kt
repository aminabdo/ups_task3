package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.home.ui.aboutUs.AboutUsFragment
import com.shoohna.happytimes.ui.home.ui.more.MoreFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MoreScreenTest{

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
        navController.navigate(R.id.moreFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, MoreFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    @Test
    fun settingConstraintClick(){
        onView(withId(R.id.settingConstraintId)).perform(click())
    }
    @Test
    fun aboutUsConstraintClick(){
        onView(withId(R.id.aboutUsConstraintId)).perform(click())
    }
    @Test
    fun contactUsConstraintClick(){
        onView(withId(R.id.contactUsConstraintId)).perform(click())
    }

    @Test
    fun logOutConstraintClick(){
        onView(withId(R.id.logOutConstraintId)).perform(click())
    }

}