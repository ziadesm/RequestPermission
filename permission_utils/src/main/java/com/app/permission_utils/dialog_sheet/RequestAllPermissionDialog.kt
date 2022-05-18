package com.app.permission_utils.dialog_sheet
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.app.permission_utils.R
import com.app.permission_utils.base.ParentDialogFragment
import com.app.permission_utils.models.PermissionRequestModel
import com.app.permission_utils.view_pager.ImagePermissionFragment
import com.app.permission_utils.view_pager.PermissionImagesViewPager
import com.google.android.material.tabs.TabLayout

class RequestAllPermissionDialog: ParentDialogFragment(R.layout.request_all_permission_dialog) {
    override val cancellable: Boolean
        get() = true
    override val listener: DialogInterface.OnCancelListener
        get() = this
    override val dismiss: DialogInterface.OnDismissListener?
        get() = null

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var model: PermissionRequestModel
    override fun initializeComponents(view: View) {
        initialize(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            model = it.getSerializable("permission_model") as PermissionRequestModel
        }
    }

    private fun initialize(view: View) {
        mPermissionRequest.launch(model.permissions.toTypedArray())
        viewPager = view.findViewById(R.id.locationImages)
        tabLayout = view.findViewById(R.id.tab_layout)
        view.findViewById<TextView>(R.id.tvCancel).setOnClickListener { dismissAllowingStateLoss() }
        view.findViewById<TextView>(R.id.tvSettings).setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", this.requireContext().packageName, null)
            intent.data = uri
            startActivity(intent)
        }

//        val item = listOf()
//        init(item)
    }

    private val mListRejected by lazy { arrayListOf<String>() }
    private val mPermissionRequest = this.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach { entries ->
            if (!entries.value) { mListRejected.add(entries.key) }
        }
    }

    private fun init(list: List<Int>) {
        val fragments: ArrayList<Fragment> = ArrayList()
        val images: List<Int> = list
        for (image in images) {
            val fragment = ImagePermissionFragment.getInstance(image)
            fragments.add(fragment)
        }
        val pagerAdapter = PermissionImagesViewPager(childFragmentManager, fragments)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager, false)
        viewPager.currentItem = 0
        viewPager.isSaveEnabled = false
    }

    companion object {
        fun getInstance(buildPermission: PermissionRequestModel): RequestAllPermissionDialog {
            val fragment = RequestAllPermissionDialog()
            val bundle = Bundle()
            bundle.putSerializable("permission_model", buildPermission)
            fragment.arguments = bundle
            return fragment
        }
    }
}