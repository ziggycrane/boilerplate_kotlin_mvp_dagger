package com.ziggycrane.blueorange.ui.auth

import android.content.Context
import android.util.Log
import com.ziggycrane.blueorange.R
import com.ziggycrane.blueorange.data.preferences.UserPreferences
import com.ziggycrane.blueorange.di.ActivityContext
import com.ziggycrane.blueorange.ui.base.BasePresenter
import com.ziggycrane.blueorange.utils.HashUtils
import com.ziggycrane.blueorange.utils.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class AuthPresenter<V : AuthContract.View> @Inject
constructor(@param:ActivityContext private val context: Context,
            private val userPreferences: UserPreferences,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider, compositeDisposable), AuthContract.Presenter<V> {

    override fun onSignInClick(id: String, password: String) {
        if (!validateData(id, password)) {
            return
        }

        mvpView!!.showLoading()

        doAuth(id, password)
    }

    private fun validateData(id: String, password: String): Boolean {
        var isValid = true
        if (!mvpView!!.isNetworkConnected()) {
            mvpView!!.onError(R.string.no_internet_connection)
            isValid = false
        }

        if (id.isEmpty()) {
            mvpView!!.onFormError(AuthContract.View.Field.ID, R.string.sign_in_empty_id)
            isValid = false
        }

        if (password.isEmpty()) {
            mvpView!!.onFormError(AuthContract.View.Field.PASSWORD, R.string.sign_in_empty_password)
            isValid = false
        }

        return isValid
    }

    private fun doAuth(id: String, password: String) {
        compositeDisposable.add(
            Observable.zip(Observable.just(id), Observable.just(password), BiFunction<String, String, Boolean> { id, password ->
                var isValid = false

                if (HashUtils.sha1(id) == "8CB2237D0679CA88DB6464EAC60DA96345513964" &&
                    HashUtils.sha1(password) == "F42A3FABE1E9BED059D727F47EB752E3AA61B977") {
                    isValid = true
                }

                isValid
            })
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(Consumer<Boolean> { response ->

                    mvpView!!.hideLoading()

                    if (!response) {
                        mvpView!!.onFormError(AuthContract.View.Field.PASSWORD, R.string.sign_in_id_incorrect)
                        return@Consumer
                    }

                    userPreferences.setLoggedIn(true)

                    if (!isViewAttached) {
                        return@Consumer
                    }

                    mvpView!!.setSignInSucessfull()
                })
        )
    }

}
