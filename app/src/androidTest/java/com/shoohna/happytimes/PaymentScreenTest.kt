package com.shoohna.happytimes

import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.shoohna.happytimes.ui.home.MainActivity
import com.shoohna.happytimes.ui.home.ui.cart.CartFragment
import com.shoohna.happytimes.ui.home.ui.checkOutProcess.paymentWay.PaymentWayFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PaymentScreenTest {

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
        navController.navigate(R.id.paymentWayFragment)

        activity = mActivityTestRule.activity
        mActivityTestRule.activity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, PaymentWayFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
    @Test
    fun loadData(){

    }
    @Test
    fun nextClick(){
        onView(withId(R.id.nextBtnId)).perform(click())
    }
    @Test
    fun allowPaymentOnDeliveryClick(){
        onView(withId(R.id.allowPaymentOnDelivery)).perform(click())
    }
    @Test
    fun allowPaymentOnlineClick(){
        onView(withId(R.id.allowPaymentOnline)).perform(click())
    }
    @Test
    fun coupanClick(){
        onView(withId(R.id.codeET)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.button3)).perform(click())
    }

}