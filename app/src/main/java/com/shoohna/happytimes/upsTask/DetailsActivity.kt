package com.shoohna.happytimes.upsTask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shoohna.happytimes.R
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val newString: String?
        newString = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getString("key")
        } else {
            savedInstanceState.getSerializable("key") as String?
        }

        tv_details.text = newString
    }
}
