package com.ziggycrane.boilerplate.ui.splash

import android.content.Context
import android.os.Handler
import com.ziggycrane.boilerplate.data.preferences.UserPreferences
import com.ziggycrane.boilerplate.di.ActivityContext
import com.ziggycrane.boilerplate.ui.base.BasePresenter
import com.ziggycrane.boilerplate.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : SplashContract.View> @Inject
    constructor(@param:ActivityContext private val context: Context,
                private var userPreferences: UserPreferences,
                schedulerProvider: SchedulerProvider,
                compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider, compositeDisposable), SplashContract.Presenter<V> {

    override fun onAttach(screen: V) {
        super.onAttach(screen)

        Handler().postDelayed(SplashRunnable(), 1500)
    }

    private fun decideNextActivity() {
        if (userPreferences.getLoggedIn()) {
            mvpView!!.openMainActivity()
        } else {
            mvpView!!.openLoginActivity()
        }
    }

    inner class SplashRunnable : Runnable {
        override fun run() {
            if (!isViewAttached) {
                return
            }

            decideNextActivity()
        }
    }

    companion object {
        private val TAG = "SplashPresenter"
    }
}