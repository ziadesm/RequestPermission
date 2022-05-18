package com.app.permission_utils.builder.request_builder

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface RequestPermission {
    fun create(fragment: Fragment): RequestPermission
    fun create(activity: AppCompatActivity): RequestPermission

    fun permissions(permission: ArrayList<String>): RequestPermission

    fun isSensitive(sensitive: Boolean): RequestPermission
    fun isRequired(require: Boolean): RequestPermission
    fun setDrawables(drawables: ArrayList<Int>): RequestPermission
    fun setImages(drawables: ArrayList<String>): RequestPermission
    fun setTitle(title: String): RequestPermission
    fun setDescription(desc: String): RequestPermission
    fun build(): RequestPermission
}