package com.ziggycrane.boilerplate.ui.base

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import butterknife.Unbinder
import com.ziggycrane.boilerplate.BlueOrangeApplication
import com.ziggycrane.boilerplate.R
import com.ziggycrane.boilerplate.di.components.ActivityComponent
import com.ziggycrane.boilerplate.di.components.DaggerActivityComponent
import com.ziggycrane.boilerplate.di.modules.activity.ActivityModule
import com.ziggycrane.boilerplate.ui.error.ErrorDialogFragment
import com.ziggycrane.boilerplate.utils.NetworkUtils

abstract class BaseActivity : AppCompatActivity(), BaseContract.BaseView, BaseFragment.Callback {

    lateinit var activityComponent: ActivityComponent

    private var unBinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = applicationContext as BlueOrangeApplication

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((app).component)
                .build()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
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

    companion object {

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        val TAG = BaseActivity::class.java.simpleName

        private val REQUEST_STORAGE = 1
    }
}