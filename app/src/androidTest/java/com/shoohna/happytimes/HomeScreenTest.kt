package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.e_commercehappytimes.ui.home.ui.home.HomeFragment
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.home.ui.help.HelpFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeScreenTest {
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
        navController.navigate(R.id.homeFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, HomeFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    @Test
    fun loadData(){

    }


    @Test
    fun homeIconClick(){
        onView(withId(R.id.bottomHomeConstraintId)).perform(click())
    }
    @Test
    fun cartIconClick(){
        onView(withId(R.id.bottomCartConstraintId)).perform(click())
    }
    @Test
    fun favoriteIconClick(){
        onView(withId(R.id.bottomFavoriteConstraintId)).perform(click())
    }
    @Test
    fun profileIconClick(){
        onView(withId(R.id.bottomProfileConstraintId)).perform(click())
    }
    @Test
    fun moreIconClick(){
        onView(withId(R.id.bottomMoreConstraintId)).perform(click())
    }


}