package com.ziggycrane.blueorange.ui.auth

import android.content.Context
import com.ziggycrane.blueorange.di.ActivityContext
import com.ziggycrane.blueorange.ui.base.BasePresenter
import com.ziggycrane.blueorange.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AuthPresenter<V : AuthContract.View> @Inject
constructor(@param:ActivityContext private val context: Context,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider, compositeDisposable), AuthContract.Presenter<V>
