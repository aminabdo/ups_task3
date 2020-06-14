package com.shoohna.happytimes.upsTask.ui.titles

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoohna.happytimes.networking.interfaces.ups
import com.shoohna.happytimes.pojo.ups.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class TitelsViewModel (private val serviceGeneral: ups)  : ViewModel() {
    private var mostPopularList: MutableLiveData<List<Result>>? = null
    var loader = MutableLiveData<Boolean>(false)


    private fun loadData(context: Context) {

        loader.value = true

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
                                loader.value = false
                                //Toast.makeText(context , "done" , Toast.LENGTH_LONG).show()
                            } else {
                                Log.i("loadData1", it.message().toString())
                                loader.value = false
                                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } catch (e: Exception) {
                            Log.i("loadData2", e.message.toString())
                            loader.value = false
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
}