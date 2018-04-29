package com.ziggycrane.blueorange.ui.auth

import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.base.BaseContract

class SplashContract {

    interface View : BaseContract.BaseView {

        fun openDashboardActivity()

    }

    @ActivityScope
    interface Presenter<in V : View> : BaseContract.BasePresenter<V> {

    }

}
