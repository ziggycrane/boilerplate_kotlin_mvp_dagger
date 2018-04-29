package com.ziggycrane.blueorange

import android.app.Application
import com.ziggycrane.blueorange.di.components.ApplicationComponent
import com.ziggycrane.blueorange.di.components.DaggerApplicationComponent
import com.ziggycrane.blueorange.di.modules.ApiModule
import com.ziggycrane.blueorange.di.modules.ApplicationModule


/**
 * Created by loop on 09/12/14.
 */

class BlueOrangeApplication : Application() {

    private lateinit var context: BlueOrangeApplication

    private var component: ApplicationComponent? = null

    fun getComponent(): ApplicationComponent? {
        return component
    }

    override fun onCreate() {
        super.onCreate()

        context = this

        initializeComponent()
        getComponent()?.inject(this)
    }

    private fun initializeComponent() {

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(context))
                .apiModule(ApiModule(BuildConfig.BASE_URL))
                .build()
    }
}