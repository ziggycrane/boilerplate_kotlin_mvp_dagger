package com.ziggycrane.blueorange.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.ziggycrane.blueorange.R
import com.ziggycrane.blueorange.ui.base.BaseActivity
import com.ziggycrane.blueorange.ui.splash.SplashContract
import javax.inject.Inject


class DashboardActivity : BaseActivity(), DashboardContract.View {

    @Inject
    lateinit var presenter: DashboardContract.Presenter<@JvmSuppressWildcards DashboardContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.inject(this)

        setUnBinder(ButterKnife.bind(this))

        presenter.onAttach(this@DashboardActivity)
    }

    override fun setUp() {

    }
}