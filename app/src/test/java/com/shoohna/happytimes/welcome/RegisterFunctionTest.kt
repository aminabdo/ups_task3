package com.shoohna.happytimes.welcome

import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import com.shoohna.happytimes.R
import com.shoohna.happytimes.upsTask.ui.titles.TitelsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.shoohna.happytimes.networking.interfaces.ups
import com.shoohna.happytimes.pojo.ups.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

import org.koin.android.ext.android.inject

import org.koin.android.ext.android.inject

//import org.koin.java.KoinJavaComponent.inject

class RegisterFunctionTest (private val serviceGeneral: ups) : ViewModel(){

    private var mostPopularList: MutableLiveData<List<Result>>? = null

    var vm: TitelsViewModel   by inject()

    private fun loadData(context: Context) {


        try {
//            val service = ApiClient.makeRetrofitServiceHome()
            CoroutineScope(Dispatchers.IO).async {

                runCatching {
                    serviceGeneral.get_UPS()
                }.onSuccess {
                    withContext(Dispatchers.Main) {
                        try {
                            Log.e("response","response 6666 ==> "+ it.body()!!.copyright)

                            if (it.isSuccessful ) {
                                Log.e("response","response 232 ==> "+ it.body()!!.copyright)
//                            Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT)
//                                .show()
//                        baseFragment.showDialog(context,response.body()!!.message)
                                mostPopularList!!.postValue(it.body()!!.results)
                                //Toast.makeText(context , "done" , Toast.LENGTH_LONG).show()
                            } else {
                                Log.i("loadData1", it.message().toString())
                                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } catch (e: Exception) {
                            Log.i("loadData2", e.message.toString())
                            Toast.makeText(
                                context,
                                "Exception ${e.message.toString()}",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }
                }.onFailure {
                    Toast.makeText(context,it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }catch (e:Exception)
        {
            Toast.makeText(context,e.message.toString(), Toast.LENGTH_SHORT).show()

        }

    }

    internal fun getTitles(context: Context): MutableLiveData<List<Result>> {
        if (mostPopularList == null) {
            mostPopularList = MutableLiveData()
            loadData(context)
        }
        return mostPopularList as MutableLiveData<List<Result>>
    }
//    var activity : WelcomeActivity = WelcomeActivity()
//
//
//
//    @Test
//    fun registerFunctionSuccess() {
//        vm.fName = MutableLiveData<String>("bob")
//        vm.lName = MutableLiveData<String>("heba")
//        vm.phone = MutableLiveData<String>("01060945044")
//        vm.password = MutableLiveData<String>("55555555")
//        vm.confirmPasssword = MutableLiveData<String>("55555555")
//        vm.email = MutableLiveData<String>("hebaMuhammd@gmail.com")
//        vm.loader = MutableLiveData<Boolean>(false)
//
//        val fNameLayout: TextInputLayout = activity.findViewById(R.id.fNameLayout)
//        val lNameLayout: TextInputLayout = activity.findViewById(R.id.lNameLayout)
//        val emailLayout: TextInputLayout = activity.findViewById(R.id.emailLayout)
//        val phoneLayout: TextInputLayout = activity.findViewById(R.id.phoneLayout)
//        val passwordLayout: TextInputLayout = activity.findViewById(R.id.passwordLayout)
//        val confirmPasswordLayout: TextInputLayout = activity.findViewById(R.id.confirmPasswordLayout)
//
//        vm.register(activity!!.view, fNameLayout , lNameLayout, emailLayout, phoneLayout, passwordLayout, confirmPasswordLayout )
//
//
//    }
}




