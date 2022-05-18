package com.app.permission_utils.builder
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.permission_utils.builder.request_builder.RequestPermission
import com.app.permission_utils.dialog_sheet.RequestAllPermissionDialog
import com.app.permission_utils.models.PermissionRequestModel

class RequestAccessPermission: RequestPermission {

    private lateinit var act: AppCompatActivity
    private lateinit var fragment: Fragment
    private lateinit var permissions: ArrayList<String>
    private var isSensitive = false
    private var isRequired = false
    private var drawables: ArrayList<Int>
    private var images: ArrayList<String>
    private var title: String
    private var desc: String

    init {
        drawables = arrayListOf()
        images = arrayListOf()
        title = ""
        desc = ""
    }

    override fun create(fragment: Fragment): RequestPermission {
        this.fragment = fragment
        return this
    }

    override fun create(activity: AppCompatActivity): RequestPermission {
        act = activity
        return this
    }

    override fun permissions(permission: ArrayList<String>): RequestPermission {
        permissions = permission
        return this
    }

    override fun isSensitive(sensitive: Boolean): RequestPermission {
        isSensitive = sensitive
        return this
    }

    override fun isRequired(require: Boolean): RequestPermission {
        isRequired = require
        return this
    }

    override fun setDrawables(drawables: ArrayList<Int>): RequestPermission {
        this.drawables = drawables
        return this
    }

    override fun setImages(drawables: ArrayList<String>): RequestPermission {
        images = drawables
        return this
    }

    override fun setTitle(title: String): RequestPermission {
        this.title = title
        return this
    }

    override fun setDescription(desc: String): RequestPermission {
        this.desc = desc
        return this
    }

    override fun build(): RequestPermission {
        val model = PermissionRequestModel(
            isSensitive,
            isRequired,
            title,
            desc,
            images,
            drawables,
            permissions
        )
        if (::fragment.isInitialized) 
            RequestAllPermissionDialog.getInstance(model).show(fragment.childFragmentManager, "PermissionFragment")
        else
            RequestAllPermissionDialog.getInstance(model).show(act.supportFragmentManager, "PermissionActivity")
        return this
    }

}