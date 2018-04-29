package com.ziggycrane.blueorange.ui.auth

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.ziggycrane.blueorange.R
import com.ziggycrane.blueorange.ui.base.BaseActivity
import com.ziggycrane.blueorange.ui.dashboard.DashboardActivity
import javax.inject.Inject

class AuthActivity: BaseActivity(), AuthContract.View {

    @BindView(R.id.sign_in_id)
    lateinit var idView: EditText

    @BindView(R.id.sign_in_password)
    lateinit var passwordView: EditText

    @BindView(R.id.id_error_container)
    lateinit var idErrorContainerView: LinearLayout

    @BindView(R.id.identificator_error_view)
    lateinit var identificatorErrorView: TextView

    @BindView(R.id.password_error_container)
    lateinit var passwordErrorContainerView: LinearLayout

    @BindView(R.id.password_error_view)
    lateinit var passwordErrorView: TextView

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

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDetach()
    }

    @OnClick(R.id.sign_in_confirm)
    internal fun onConfirm() {
        hideAllErrors()

        presenter.onSignInClick(
            idView.text.toString(),
            passwordView.text.toString()
        )
    }

    fun hideAllErrors() {
        idErrorContainerView.visibility = View.GONE
        passwordErrorContainerView.visibility = View.GONE
    }

    override fun onFormError(field: AuthContract.View.Field, @StringRes resId: Int) {
        when (field) {
            AuthContract.View.Field.ID -> {
                identificatorErrorView.setText(resId)
                idErrorContainerView.visibility = View.VISIBLE
            }
            AuthContract.View.Field.PASSWORD -> {
                passwordErrorView.setText(resId)
                passwordErrorContainerView.visibility = View.VISIBLE
            }
        }
    }

    override fun setSignInSucessfull() {
        openDashboardActivity()
    }

    fun openDashboardActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}
