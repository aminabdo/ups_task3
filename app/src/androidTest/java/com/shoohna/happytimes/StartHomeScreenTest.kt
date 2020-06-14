package com.shoohna.happytimes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.welcome.WelcomeActivity
import com.shoohna.happytimes.ui.welcome.ui.welcome.WelcomeFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class StartHomeScreenTest {
    private lateinit var welcomeActivity : WelcomeActivity

    @Rule @JvmField
    var mActivityRule = ActivityTestRule(WelcomeActivity::class.java)

    @Before
    fun setUp() {
        welcomeActivity = mActivityRule.activity

        welcomeActivity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, WelcomeFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
    @Test
    fun testGoSkipScreen() {
        onView(withId(R.id.skipTxtViewId)).perform(click())
    }

}