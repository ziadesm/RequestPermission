package com.app.permissionaccess
import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.permission_utils.builder.RequestAccessPermission

class RequestAllPermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_all_permission)

        RequestAccessPermission()
            .create(this)
            .permissions(arrayListOf(Manifest.permission.ACCESS_COARSE_LOCATION))
            .setDescription("Your Desc")
            .setTitle("Your Title")
            .setDrawables(arrayListOf(R.mipmap.ic_launcher))
            .setImages(arrayListOf("https://www.yourimageurl.com"))
            .isRequired(true)
            .isSensitive(true)
            .build()
    }
}