package com.shoohna.shoohna.util.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SyncContext
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.shoohna.happytimes.R

open class BaseViewModel : ViewModel() {

    var modelData = MutableLiveData<String>()
    var liveList = MutableLiveData<ArrayList<String>>()


    fun checkEmpty(textInputLayout:TextInputLayout, data:String , message:String): Boolean {
        if(TextUtils.isEmpty(data)){
            textInputLayout.error = message;

            textInputLayout.hasFocus()
            textInputLayout.requestFocus()
            return true
        }
        textInputLayout.error = "";
        textInputLayout.isErrorEnabled = false
        return false
    }

    fun checkValidEmail(textInputLayout:TextInputLayout, data:String , message:String): Boolean {
        if(!data.contains("@") || !data.contains(".")){
            textInputLayout.error = message;

            textInputLayout.hasFocus()
            textInputLayout.requestFocus()
            return true
        }
        textInputLayout.error = "";
        textInputLayout.isErrorEnabled = false
        return false
    }
    fun checkValidPassword(textInputLayout:TextInputLayout, data:String , message:String): Boolean {
        if(data.length < 7){
            textInputLayout.error = message;

            textInputLayout.hasFocus()
            textInputLayout.requestFocus()
            return true
        }
        textInputLayout.error = "";
        textInputLayout.isErrorEnabled = false
        return false
    }
    fun checkEquals(textInputLayout:TextInputLayout, data:String, data2:String, message: String): Boolean {
        if(!data.equals(data2)){
            textInputLayout.error = message;
            return true
        }

        //
        textInputLayout.error = "";
        textInputLayout.isErrorEnabled = false
        return false
    }

     fun showDialog(title: String , v : View) {
        val dialog = Dialog(v!!.context)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(false)
//        dialog .setContentView(R.layout.foreground_notification_dialog)
//        val Title = dialog .findViewById(R.id.Title) as TextView
//        val GoToLogin = dialog .findViewById(R.id.GoToLogin) as Button
//         GoToLogin.setOnClickListener {
//             var intent : Intent = Intent (v!!.context , WelcomeActivity::class.java)
//             v!!.context.startActivity(intent)
//             (v.context as Activity).finish()
//         }
//        Title.text = title
        dialog .show()

    }




}