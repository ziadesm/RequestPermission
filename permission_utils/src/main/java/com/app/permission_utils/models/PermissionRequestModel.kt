package com.app.permission_utils.models

import java.io.Serializable

data class PermissionRequestModel(
    var isSensitive: Boolean,
    var isRequire: Boolean,
    val title: String,
    val desc: String,
    val images: ArrayList<String> = arrayListOf(),
    val drawables: ArrayList<Int> = arrayListOf(),
    val permissions: ArrayList<String> = arrayListOf(),
): Serializable
