package com.app.permission_utils.view_pager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.app.permission_utils.R

class ImagePermissionFragment: Fragment() {
    @DrawableRes private var imageModel: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            imageModel = requireArguments().getInt("image")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_image_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
    }

    private fun init(view: View) {
        if (imageModel != null) {
            Log.d("ImageModelFragment", "init: >>> $imageModel")
            view.findViewById<AppCompatImageView>(R.id.imageItem)
                .setImageResource(imageModel ?: R.drawable.ic_launcher_background)
        }
    }

    companion object {
        fun getInstance(@DrawableRes image: Int?): ImagePermissionFragment {
            val fragment = ImagePermissionFragment()
            if (image != null) {
                val bundle = Bundle()
                bundle.putInt("image", image)
                fragment.arguments = bundle
            }
            return fragment
        }
    }
}