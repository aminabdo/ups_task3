package com.shoohna.shoohna.util.base

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.shoohna.happytimes.R
import com.shoohna.happytimes.util.base.Constants
import com.shoohna.happytimes.util.base.SharedHelper
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream
import java.io.IOException

open class BaseFragment : Fragment() {
    private lateinit var progress: ProgressDialog
    private val PERMISSIONS_REQUEST : Int = 1
    private val GALLERY_REQUEST : Int = 2
    private val CAMERA_REQUEST : Int = 3
    lateinit var imageView: ImageView
    lateinit var imgUri : Uri
    var sharedHelper: SharedHelper = SharedHelper()
    fun showShortToast(message:String)
    {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(message: String)
    {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }

    //,positiveButtonText: String,negativeButtonText: String,positiveClick:View.OnClickListener,negativeClick:View.OnClickListener
    fun showProgressDialog(context: Context, title: String, message: String, cancellable :Boolean)
    {
        try {
            progress = ProgressDialog(context)
            progress.setMessage(message)
            progress.setTitle(title)
            progress.setCancelable(cancellable)

            progress.show()
        }catch (e:Exception){
            Log.e("error " , "error 2020")
            progress.dismiss()
        }
    }

    fun dismissProgressDialog()
    {
        progress.dismiss()
    }

    fun checkPermissions() : Boolean
    {
        return (activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } != PackageManager.PERMISSION_GRANTED
                && activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) } != PackageManager.PERMISSION_GRANTED
                && activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.WRITE_EXTERNAL_STORAGE) } != PackageManager.PERMISSION_GRANTED
                && activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) } != PackageManager.PERMISSION_GRANTED
                && activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.INTERNET) } != PackageManager.PERMISSION_GRANTED)
    }

    fun askForPermissions()
    {
        activity?.let {
            ActivityCompat.requestPermissions(
                it, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION
                    ,Manifest.permission.READ_EXTERNAL_STORAGE
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,Manifest.permission.CAMERA
                    ,Manifest.permission.INTERNET)
                ,PERMISSIONS_REQUEST)
            Log.i("InsideBaseFragment1","true")

        }
        Log.i("InsideBaseFragment2","true")
    }

    fun askForPermissionsImage()
    {
        activity?.let {
            ActivityCompat.requestPermissions(
                it, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,Manifest.permission.CAMERA)
                ,PERMISSIONS_REQUEST)
        }
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
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED
                    && grantResults[4] == PackageManager.PERMISSION_GRANTED)
                {
                    showShortToast(resources.getString(R.string.permissionGranted))
                } else {
                    showShortToast(resources.getString(R.string.permissionDenied))
                }
                return
            }
        }
    }

    fun gotoAnotherActivity(activityToGo: Activity)
    {
        startActivity(Intent(activity, activityToGo::class.java))
    }

    fun openGalleryIntent(view: CircleImageView) {
        this.imageView = view
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent , GALLERY_REQUEST)
    }

    fun openCameraIntent(view: CircleImageView) {
        this.imageView = view
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            this.imgUri = data?.data!!
            imageView.setImageURI(imgUri)
            Log.d("URI" , "URI"+imgUri)
        }
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val thumbnail = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(thumbnail)
            val deadnaimg : Bitmap = thumbnail
            val byteArrayOutputStream =
                ByteArrayOutputStream()
            if (deadnaimg != null) {
                val ImageResultUri : Uri = data?.data!!
//                Log.d("URI" , "URI"+imgUri)
            }
        }
    }


    fun verifyAvailableNetwork(context:Context):Boolean{
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }


    fun showPictureDialog( v: CircleImageView) {
        val pictureDialog =
            AlertDialog.Builder(activity)
        pictureDialog.setTitle(getString(R.string.ChooseImage))
        val pictureDialogItems = arrayOf(
            getString(R.string.gallery),
            getString(R.string.camera)
        )
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                1 -> if (checkPermissionImage()) {
                    askForPermissionsImage()
                } else
                {
                    openCameraIntent(v)
                }
                0 -> if (checkPermissionGallery()) {
                    askForPermissionsImage()
                } else {
                    openGalleryIntent(v)
                }
            }
        }
        pictureDialog.show()
    }

    fun checkPermissionImage() : Boolean
    {
        return ( activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) } != PackageManager.PERMISSION_GRANTED)
    }
    fun checkPermissionGallery() : Boolean
    {
        return ( activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) } != PackageManager.PERMISSION_GRANTED)
    }


    fun showDialog( context: Context , message:String) {

        var dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setCancelable(true)
        dialog.setMessage(message)
        dialog.setPositiveButton("Ok",null)
        dialog.create().show()
    }




    fun share (v : View)
    {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = "Application Link : https://play.google.com/store/apps/details?id=${v.rootView.context.packageName}"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "App link")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        v.rootView.context.startActivity(Intent.createChooser(sharingIntent, "Share App Link Via :"))
    }
}