package com.ziggycrane.blueorange.ui.auth

import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.base.BaseContract

class AuthContract {

    interface View : BaseContract.BaseView {

    }

    @ActivityScope
    interface Presenter<in V : View> : BaseContract.BasePresenter<V> {

    }

}
