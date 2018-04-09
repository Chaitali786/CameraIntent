package com.example.tmp_sda_1180.kotlincameraintent

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       btnCamera.isEnabled = hasCamera()
        if(btnCamera.isEnabled == true){

            btnCamera.setOnClickListener {

                val callcameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                if(callcameraIntent.resolveActivity(packageManager)!=null){
                    startActivityForResult(callcameraIntent,CAMERA_REQUEST_CODE)
                }
            }
        }else {
            Toast.makeText(this,"No Front Camera Is Available", Toast.LENGTH_SHORT).show()
        }

    }

    private fun hasCamera(): Boolean {
        return packageManager.hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(resultCode == Activity.RESULT_OK && data != null){
                    photoimageview.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }else -> {
                    Toast.makeText(this,"Unrecognised Request Code", Toast.LENGTH_SHORT).show()
        }

        }
    }
}
