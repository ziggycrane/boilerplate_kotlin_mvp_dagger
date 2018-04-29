package com.ziggycrane.blueorange.ui.splash

import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.base.BaseContract

class SplashContract {

    interface View : BaseContract.BaseView {

        fun openLoginActivity()
        fun openMainActivity()

    }

    @ActivityScope
    interface Presenter<in V : View> : BaseContract.BasePresenter<V>

}
