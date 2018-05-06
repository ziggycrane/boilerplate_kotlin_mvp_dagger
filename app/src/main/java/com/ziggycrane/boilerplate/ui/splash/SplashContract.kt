package com.ziggycrane.boilerplate.ui.splash

import com.ziggycrane.boilerplate.di.scopes.ActivityScope
import com.ziggycrane.boilerplate.ui.base.BaseContract

class SplashContract {

    interface View : BaseContract.BaseView {

        fun openLoginActivity()
        fun openMainActivity()

    }

    @ActivityScope
    interface Presenter<in V : View> : BaseContract.BasePresenter<V>

}
