package com.ziggycrane.boilerplate.ui.dashboard

import android.content.Context
import android.util.Log
import com.ziggycrane.boilerplate.data.preferences.BasePreferences
import com.ziggycrane.boilerplate.data.preferences.UserPreferences
import com.ziggycrane.boilerplate.di.ActivityContext
import com.ziggycrane.boilerplate.ui.base.BasePresenter
import com.ziggycrane.boilerplate.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DashboardPresenter<V : DashboardContract.View>

    @Inject
    constructor(@param:ActivityContext private val context: Context,
                internal var basePreferences: BasePreferences,
                internal var userPreferences: UserPreferences,
                schedulerProvider: SchedulerProvider,
                compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider, compositeDisposable), DashboardContract.Presenter<V> {

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)

    }

    companion object {
        private val TAG = "DashboardPresenter"
    }
}