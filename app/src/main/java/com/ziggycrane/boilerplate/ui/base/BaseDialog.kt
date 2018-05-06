package com.ziggycrane.boilerplate.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import butterknife.Unbinder
import com.ziggycrane.boilerplate.di.components.ActivityComponent

abstract class BaseDialog : DialogFragment(), BaseContract.BaseDialogView {

    var baseActivity: BaseActivity? = null
        private set
    private var unBinder: Unbinder? = null
    private var loadingScreenWasShown: Boolean = false
    private var afterOnStart: Boolean = false

    override fun isNetworkConnected(): Boolean {
        return if (baseActivity != null) {
            baseActivity!!.isNetworkConnected()
        } else {
            false
        }
    }

    val activityComponent: ActivityComponent?
        get() {
            return if (baseActivity != null) {
                baseActivity!!.activityComponent
            } else null
        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val mActivity = context as BaseActivity?
            this.baseActivity = mActivity
            mActivity!!.onFragmentAttached()
        }
    }

    override fun showLoading() {
        if (baseActivity != null) {
            baseActivity!!.showLoading()
            loadingScreenWasShown = true
        }
    }

    override fun hideLoading() {
        if (baseActivity != null) {
            baseActivity!!.hideLoading()
            loadingScreenWasShown = false
        }
    }

    override fun onStart() {
        super.onStart()
        if (loadingScreenWasShown && baseActivity != null) {
            baseActivity!!.showLoading()
        }
        afterOnStart = true
    }

    override fun onStop() {
        afterOnStart = false
        if (loadingScreenWasShown && baseActivity != null) {
            baseActivity!!.hideLoading()
        }
        super.onStop()
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onError(message: String?) {
        if (baseActivity != null) {
            baseActivity!!.onError(message)
        }
    }

    override fun onError(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.onError(resId)
        }
    }

    fun setUnBinder(unBinder: Unbinder) {
        this.unBinder = unBinder
    }

    protected abstract fun setUp(view: View)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = FrameLayout(activity!!)
        root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        // creating the fullscreen dialog
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
        }
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun show(fragmentManager: FragmentManager, tag: String) {
        try {
            val transaction = fragmentManager.beginTransaction()
            val prevFragment = fragmentManager.findFragmentByTag(tag)
            if (prevFragment != null) {
                transaction.remove(prevFragment)
            }
            transaction.addToBackStack(null)
            show(transaction, tag)
        } catch (e: IllegalStateException) {
            //
        }

    }

    protected fun dismissDialog(tag: String) {
        dismiss()
        baseActivity!!.onFragmentDetached(tag)
    }

    override fun onDestroy() {
        if (unBinder != null) {
            unBinder!!.unbind()
        }
        super.onDestroy()
    }
}