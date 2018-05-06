package com.ziggycrane.boilerplate.ui.dashboard

import com.ziggycrane.boilerplate.di.scopes.ActivityScope
import com.ziggycrane.boilerplate.ui.base.BaseContract

class DashboardContract {

    interface View : BaseContract.BaseView

    @ActivityScope
    interface Presenter<in V : DashboardContract.View> : BaseContract.BasePresenter<V>

}
