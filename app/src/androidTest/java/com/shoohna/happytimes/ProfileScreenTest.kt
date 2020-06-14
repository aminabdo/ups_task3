package com.shoohna.happytimes

import android.view.View
import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.home.ui.profile.ProfileFragment
import com.shoohna.happytimes.ui.welcome.WelcomeActivity
import com.shoohna.happytimes.util.base.SharedHelper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ProfileScreenTest {

    private lateinit var activity : MainActivity
    private lateinit var navController : TestNavHostController

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)



    @Before
    fun setUp() {
        val sharedHelper = SharedHelper()
        sharedHelper.putKey(mActivityTestRule.activity!! ,"OPEN", "OPEN")

        navController = TestNavHostController(mActivityTestRule.activity!!)
        navController.setViewModelStore(ViewModelStore())

        navController.setGraph(R.navigation.nav_home)
        navController.navigate(R.id.profileFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, ProfileFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }


    @Test
    fun editClick(){
        onView(withId(R.id.editConstraintId)).perform(click())
    }

    @Test
    fun dontSaveImgClick(){
        onView(withId(R.id.dontSaveImgId)).perform(click())
    }

    @Test
    fun changePasswordClick(){
        onView(withId(R.id.changePasswordTxtId)).perform(click())
    }

    @Test
    fun addImageClick(){
        onView(withId(R.id.addImageBtnId)).perform(click())
    }
    @Test
    fun correctImgClick(){
        onView(withId(R.id.correctImgId)).perform(click())
    }

}