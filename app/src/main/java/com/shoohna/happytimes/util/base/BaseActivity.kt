package com.shoohna.shoohna.util.base

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.shoohna.happytimes.R

class BaseActivity(var context: Context) : AppCompatActivity() {

    private lateinit var progress:ProgressDialog
    private val PERMISSIONS_REQUEST : Int = 1
    private val GALLERY_REQUEST : Int = 2
    private val CAMERA_REQUEST : Int = 3
    lateinit var imageView: ImageView
    lateinit var imgUri : Uri

    fun showShortToast(message:String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    fun showLongToast( message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    //,positiveButtonText: String,negativeButtonText: String,positiveClick:View.OnClickListener,negativeClick:View.OnClickListener
    fun showProgressDialog(context:Context ,title: String,message: String,cancellable :Boolean) {
        progress = ProgressDialog(context)
        progress.setMessage(message)
        progress.setTitle(title)
        progress.setCancelable(cancellable)
        progress.show()
    }

    fun dismissProgressDialog() {
        progress.dismiss()
    }

    fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            askForPermissions()
    }

    private fun askForPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION
            ,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.CAMERA)
            ,PERMISSIONS_REQUEST)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode)
        {
            PERMISSIONS_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                    showShortToast(resources.getString(R.string.permissionGranted))
                } else {
                    showShortToast(resources.getString(R.string.permissionDenied))
                }
                return
            }
        }
    }

    fun gotoAnotherActivity(activity:Activity) {
        startActivity(Intent(context, activity::class.java))
    }

    fun openGalleryIntent(view:ImageView) {
        this.imageView = view
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent , GALLERY_REQUEST)
    }

    fun openCameraIntent(view:ImageView) {
        this.imageView = view
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            this.imgUri = data?.data!!
            imageView.setImageURI(imgUri)
        }
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }


    fun showDialog( context: Context) {
        val dialog = Dialog(context)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(false)
//        dialog .setContentView(R.layout.foreground_notification_dialog)
//        val Title = dialog .findViewById(R.id.Title) as TextView
//        val GoToLogin = dialog .findViewById(R.id.GoToLogin) as Button
//        GoToLogin.setOnClickListener {
//            var intent : Intent = Intent (context , WelcomeActivity::class.java)
//            context.startActivity(intent)
//            (context as Activity).finish()
//        }
//        Title.text = title
        dialog .show()

    }


}