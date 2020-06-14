package com.shoohna.happytimes.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shoohna.happytimes.R
import com.shoohna.shoohna.util.base.BaseViewModel
import com.squareup.picasso.Picasso


object BindingAdapters{

    @BindingAdapter("imageUrl")
    @JvmStatic //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/
    fun loadImage(view: ImageView, url: String) = Picasso.get().load(url).into(view)

    @BindingAdapter("loadHexaColor")
    @JvmStatic
    fun loadHexaColor(view:ImageView,color:String)
    {
        if(color != "")
            view.setBackgroundColor(Color.parseColor("#$color"))
        else
            view.setBackgroundColor(Color.parseColor("#FFFFFF"))

//        view.setColorFilter(Color.parseColor("#$color"))
//        var Color:Int = color.replace("#","").toInt()
//        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color));

    }

    @BindingAdapter("statusOfOrder")
    @JvmStatic
    fun statusOfOrder(txtView:TextView , orderNumberState:Int)
    {
        when (orderNumberState) {
            0 -> {
                txtView.text = "لم يكتمل"
                txtView.setTextColor(Color.parseColor("#EAA61D")) // TODO red
            }
            1 -> {
                txtView.text = "جديد"
                txtView.setTextColor(Color.parseColor("#EAA61D"))
            }
            2 -> {
                txtView.text = "في المعالجه"
                txtView.setTextColor(Color.parseColor("#EAA61D"))
            }
            3 -> {
//                txtView.text = R.string.pleaseInsertAllData.toString()

                txtView.text = "اكتمل"
                txtView.setTextColor(Color.parseColor("#8BC34A"))
            }
            4 -> {
                txtView.text = "تم الالغاء"
                txtView.setTextColor(Color.parseColor("#F81832"))
            }
        }

    }

//    @BindingAdapter("arrayDataProductList")
//    fun bindRecyclerView(recyclerView: RecyclerView, data: List<ProductListModel>?) {
//        val adapter = recyclerView.adapter as ProductListRecyclerViewAdapter
//        //adapter.submitList(data)
//    }

//    @BindingAdapter("notificationData")
//    @JvmStatic
//    fun setNotificationData(recyclerView: RecyclerView ,data :MutableLiveData<List<NotificationModel>>)
//    {
//        if(data.value != null)
//            recyclerView.adapter = NotificationRecyclerViewAdapter(data)
//        else
//            Log.i("NotificationData","NoData")
//    }

//    @JvmStatic
//    @BindingAdapter("arrayDataFavorites")
//    fun favorites(view: RecyclerView, modelItem: MutableLiveData<List<ProductListModel>>) {
//        (Objects.requireNonNull(view.adapter) as FavoriteRecyclerViewAdapter).setFavoriteList(modelItem)
//    }

}