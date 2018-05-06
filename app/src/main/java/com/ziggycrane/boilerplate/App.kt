package com.ziggycrane.boilerplate

import android.app.Application
import com.ziggycrane.boilerplate.di.components.ApplicationComponent
import com.ziggycrane.boilerplate.di.components.DaggerApplicationComponent
import com.ziggycrane.boilerplate.di.modules.activity.ActivityModule
import com.ziggycrane.boilerplate.di.modules.app.ApiModule
import com.ziggycrane.boilerplate.di.modules.app.ApplicationModule


/**
 * Created by loop on 09/12/14.
 */

class BlueOrangeApplication : Application() {

    lateinit var component: ApplicationComponent

    private lateinit var context: BlueOrangeApplication

    override fun onCreate() {
        super.onCreate()

        context = this

        initializeComponent()
        component.inject(this)
    }

    private fun initializeComponent() {

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(context))
                .apiModule(ApiModule(BuildConfig.BASE_URL))
                .build()
    }
}