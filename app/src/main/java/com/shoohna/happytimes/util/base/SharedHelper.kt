package com.shoohna.happytimes.util.base

import android.content.Context
import android.content.SharedPreferences


class SharedHelper {

    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    fun  putKey(context: Context, Key: String?, Value: String?) {
        sharedPreferences = context.getSharedPreferences("ECommerce", Context.MODE_PRIVATE)
        editor = sharedPreferences!!.edit()
        editor!!.putString(Key, Value)
        editor!!.commit()
    }

    fun  getKey(contextGetKey: Context, Key: String?): String? {
        sharedPreferences = contextGetKey.getSharedPreferences("ECommerce", Context.MODE_PRIVATE)
        return sharedPreferences!!.getString(Key, "")
    }


}