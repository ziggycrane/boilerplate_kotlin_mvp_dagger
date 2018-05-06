package com.ziggycrane.boilerplate.ui.error

import android.content.Context
import com.ziggycrane.boilerplate.di.ActivityContext
import com.ziggycrane.boilerplate.ui.base.BasePresenter
import com.ziggycrane.boilerplate.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ErrorDialogPresenter<V : ErrorDialogContract.View> @Inject
constructor(@param:ActivityContext private val context: Context,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider, compositeDisposable), ErrorDialogContract.Presenter<V>
