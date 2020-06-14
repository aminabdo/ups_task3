package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.welcome.WelcomeActivity
import com.shoohna.happytimes.ui.welcome.ui.resetPassword.ResetPasswordFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
public class ResetPasswordScreenTest{

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
        navController.navigate(R.id.resetPasswordFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, ResetPasswordFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    @Test
    fun resetPasswordClick(){
        onView(withId(R.id.etCode)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.etPassword)).perform(typeText("hebamuhammed@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.resetPassword)).perform(click())
    }
}