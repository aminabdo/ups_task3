package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.home.ui.moreSetting.MoreSettingFragment
import com.shoohna.happytimes.ui.home.ui.notification.NotificationFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NotificationsScreenTest{
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
        navController.navigate(R.id.notificationFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, NotificationFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    @Test
    fun loadData(){

    }
}