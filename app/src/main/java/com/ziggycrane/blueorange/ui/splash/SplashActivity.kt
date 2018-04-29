package com.ziggycrane.blueorange.ui.splash

import android.content.Intent
import android.os.Bundle
import butterknife.ButterKnife
import com.ziggycrane.blueorange.R
import com.ziggycrane.blueorange.ui.dashboard.DashboardActivity
import com.ziggycrane.blueorange.ui.auth.AuthActivity
import com.ziggycrane.blueorange.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashPresenter<SplashContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityComponent.inject(this)

        setUnBinder(ButterKnife.bind(this))

        presenter.onAttach(this@SplashActivity)
    }

    override fun openLoginActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openMainActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setUp() {

    }
}
