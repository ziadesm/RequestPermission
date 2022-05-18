package com.app.permission_utils.base
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment

abstract class ParentDialogFragment(
    private val mLayout: Int,
): DialogFragment(mLayout) {
    protected abstract val cancellable: Boolean
    protected abstract val listener: DialogInterface.OnCancelListener?
    protected abstract val dismiss: DialogInterface.OnDismissListener?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(mLayout, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window!!.setBackgroundDrawable(ResourcesCompat.getDrawable(this.resources, android.R.color.transparent, null))
        dialog?.window!!.setGravity(Gravity.CENTER)
        isCancelable = cancellable

        if (cancellable) {
            dialog?.setOnCancelListener(listener)
            dialog?.setOnDismissListener(dismiss)
        }

        initializeComponents(view)
        return view
    }

    protected abstract fun initializeComponents(view: View)
}