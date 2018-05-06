package com.ziggycrane.boilerplate.ui.base

import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager

interface BaseContract {

    interface BaseView {

        fun isNetworkConnected(): Boolean

        fun showLoading()

        fun hideLoading()

        fun onError(@StringRes resId: Int)

        fun onError(message: String?)
    }

    interface BasePresenter<in S : BaseView> {

        fun onAttach(screen: S)

        fun onDetach()

    }

    interface BaseDialogView : BaseView {

        fun dismissDialog()
        fun showDialog(manager: FragmentManager)
    }

}