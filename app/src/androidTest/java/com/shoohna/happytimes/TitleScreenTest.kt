package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.upsTask.MostPopularActivity
import com.shoohna.happytimes.upsTask.ui.titles.TitelsFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TitleScreenTest {


    private lateinit var activity : MostPopularActivity
    private lateinit var navController : TestNavHostController

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MostPopularActivity::class.java)


    @Before
    fun setUp() {
        navController = TestNavHostController(mActivityTestRule.activity!!)
        navController.setViewModelStore(ViewModelStore())

        navController.setGraph(R.navigation.nav_ups)
        navController.navigate(R.id.titles)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, TitelsFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

    @Test
    fun titleClick()
    {
        Espresso.onView(withId(R.id.tv_title)).perform(ViewActions.click())
    }
    @Test
    fun descClick()
    {
        Espresso.onView(withId(R.id.tv_desc)).perform(ViewActions.click())
    }
}