package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.welcome.WelcomeActivity
import com.shoohna.happytimes.ui.welcome.ui.welcome.WelcomeFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
public class WelcomeScreenTest {

    private lateinit var activity : WelcomeActivity
    private lateinit var navController : TestNavHostController

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(WelcomeActivity::class.java)

    @Before
    fun setUp() {
        navController = TestNavHostController(mActivityTestRule.activity!!)
        navController.setViewModelStore(ViewModelStore())

        navController.setGraph(R.navigation.nav_welcome)
        navController.navigate(R.id.welcomeFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, WelcomeFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    @Test
    fun loginClick()
    {
        onView(withId(R.id.signInBtnId)).perform(click())
    }
    @Test
    fun signUpClick()
    {
        onView(withId(R.id.signUpBtnId)).perform(click())
    }


}