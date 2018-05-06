package com.ziggycrane.blueorange.ui.dashboard

import android.content.Context
import android.util.Log
import com.ziggycrane.blueorange.data.preferences.BasePreferences
import com.ziggycrane.blueorange.data.preferences.UserPreferences
import com.ziggycrane.blueorange.di.ActivityContext
import com.ziggycrane.blueorange.ui.base.BasePresenter
import com.ziggycrane.blueorange.utils.rx.SchedulerProvider
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