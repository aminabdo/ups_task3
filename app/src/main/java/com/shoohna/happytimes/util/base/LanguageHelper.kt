package com.shoohna.happytimes.util.base

import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

class LanguageHelper {

    fun ChangeLang(resources: Resources, locale1: String?) {
        val Config: Configuration
        Config = Configuration(resources.getConfiguration())
        when (locale1) {
            "ar" -> {
                val locale = Locale(locale1)
                Config.locale = Locale("ar")
                Config.setLayoutDirection(locale)
            }
            "en" -> {
                val locale2 = Locale(locale1)
                Config.locale = Locale("en")
                Config.setLayoutDirection(locale2)
            }
        }
        resources.updateConfiguration(Config, resources.getDisplayMetrics())
    }
}