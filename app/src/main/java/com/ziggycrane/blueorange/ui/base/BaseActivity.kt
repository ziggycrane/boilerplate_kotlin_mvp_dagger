package com.ziggycrane.blueorange.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.inputmethod.InputMethodManager
import butterknife.Unbinder
import com.ziggycrane.blueorange.BlueOrangeApplication
import com.ziggycrane.blueorange.R
import com.ziggycrane.blueorange.di.components.ActivityComponent
import com.ziggycrane.blueorange.di.components.DaggerActivityComponent
import com.ziggycrane.blueorange.di.modules.ActivityModule
import com.ziggycrane.blueorange.ui.error.ErrorDialogFragment
import com.ziggycrane.blueorange.utils.NetworkUtils
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity(), BaseContract.BaseView, BaseFragment.Callback {

    lateinit var activityComponent: ActivityComponent

    private var unBinder: Unbinder? = null

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = applicationContext as BlueOrangeApplication

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as BlueOrangeApplication).getComponent())
                .build()
    }

    fun setUnBinder(unBinder: Unbinder) {
        this.unBinder = unBinder
    }

    override fun onError(message: String?) {
        var message = message
        if (message == null) {
            message = getString(R.string.some_error_happened)
        }

        val fragment = supportFragmentManager.findFragmentByTag(ErrorDialogFragment.TAG)
        if (fragment != null) {
            return
        }

        ErrorDialogFragment.newInstance(message).showDialog(supportFragmentManager)
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun onDestroy() {
        if (unBinder != null) {
            unBinder!!.unbind()
        }

        super.onDestroy()
    }

    abstract fun setUp()

    abstract fun showBlockingView()
    abstract fun hideBlockingView()

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        val TAG = BaseActivity::class.java.simpleName

        private val REQUEST_STORAGE = 1
    }
}