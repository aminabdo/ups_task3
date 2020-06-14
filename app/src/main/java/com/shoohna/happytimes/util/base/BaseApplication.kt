package com.shoohna.happytimes.util.base

//import ApiClient.netModule

import ApiClient.titlesServiceModuleMainActivity
import ApiClient.upsModule

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

//    var listofModules = module {
//        single { ApiClientGeneral() }
//    }

    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        startKoin {

            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(
                upsModule,
                titlesServiceModuleMainActivity))
        }
    }
}