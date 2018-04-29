package com.ziggycrane.blueorange.ui.auth

import android.os.Bundle
import butterknife.ButterKnife
import com.ziggycrane.blueorange.R
import com.ziggycrane.blueorange.ui.base.BaseActivity
import javax.inject.Inject

class AuthActivity: BaseActivity(), AuthContract.View {

    @Inject
    lateinit var presenter: AuthPresenter<AuthContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        activityComponent.inject(this)

        setUnBinder(ButterKnife.bind(this))

        presenter.onAttach(this@AuthActivity)
    }

    override fun setUp() {

    }

}
