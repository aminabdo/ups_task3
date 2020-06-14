package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.e_commercehappytimes.ui.welcome.ui.login.LoginFragment
import com.shoohna.happytimes.ui.welcome.WelcomeActivity
import com.shoohna.happytimes.ui.welcome.ui.forgetPassword.ForgetPasswordFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ForgetPasswordScreenTest {

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
        navController.navigate(R.id.forgetPasswordFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, ForgetPasswordFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
    @Test
    fun forgetPasswordClick(){
        onView(withId(R.id.etEmail)).perform(typeText("hebamuhammed@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.sendBtnId)).perform(click())
    }
}