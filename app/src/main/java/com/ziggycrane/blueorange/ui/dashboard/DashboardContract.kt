package com.ziggycrane.blueorange.ui.dashboard

import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.base.BaseContract

class DashboardContract {

    interface View : BaseContract.BaseView

    @ActivityScope
    interface Presenter<in V : DashboardContract.View> : BaseContract.BasePresenter<V>

}
