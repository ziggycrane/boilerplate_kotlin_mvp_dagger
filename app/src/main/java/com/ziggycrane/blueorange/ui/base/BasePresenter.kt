package com.ziggycrane.blueorange.ui.base

import com.ziggycrane.blueorange.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BasePresenter<V : BaseContract.BaseView> @Inject
constructor(val schedulerProvider: SchedulerProvider,
            val compositeDisposable: CompositeDisposable) : BaseContract.BasePresenter<V> {

    var mvpView: V? = null
        private set

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        mvpView!!.hideLoading()
        compositeDisposable.clear()
        mvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.onAttach(MainView) before" + " requesting data to the Presenter")

    companion object {

        private val TAG = "BasePresenter"
    }
}