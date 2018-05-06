package com.ziggycrane.boilerplate.ui.auth

import android.support.annotation.StringRes
import com.ziggycrane.boilerplate.di.scopes.ActivityScope
import com.ziggycrane.boilerplate.ui.base.BaseContract

class AuthContract {

    interface View : BaseContract.BaseView {
        enum class Field {
            ID, PASSWORD
        }

        fun onFormError(field: Field, @StringRes resId: Int)
        fun setSignInSucessfull()
    }

    @ActivityScope
    interface Presenter<in V : View> : BaseContract.BasePresenter<V> {
        fun onSignInClick(id: String, password: String)
    }

}
