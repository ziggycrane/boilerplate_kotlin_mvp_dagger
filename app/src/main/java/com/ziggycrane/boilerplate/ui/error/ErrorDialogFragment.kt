package com.ziggycrane.boilerplate.ui.error

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.ziggycrane.boilerplate.R
import com.ziggycrane.boilerplate.ui.base.BaseDialog
import javax.inject.Inject

class ErrorDialogFragment : BaseDialog(), ErrorDialogContract.View {

    @BindView(R.id.error_message)
    lateinit var errorMessageView: TextView

    @Inject
    lateinit var presenter: ErrorDialogContract.Presenter<ErrorDialogContract.View>

    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false

        if (arguments != null) {
            message = arguments!!.getString(ARG_MESSAGE)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.dialog_error, container, false)

        val component = activityComponent
        if (component != null) {

            component.inject(this)

            setUnBinder(ButterKnife.bind(this, view))

            presenter.onAttach(this)
        }

        return view
    }

    @OnClick(R.id.btn_close)
    internal fun onClose() {
        dismissDialog()
    }

    override fun dismissDialog() {
        super.dismissDialog(TAG)
    }

    override fun showDialog(manager: FragmentManager) {
        show(manager, TAG)
    }

    override fun setUp(view: View) {
        errorMessageView.text = message
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    companion object {

        val TAG = ErrorDialogContract::class.java.simpleName

        val ARG_MESSAGE = "ARG_MESSAGE"

        fun newInstance(message: String?): ErrorDialogFragment {
            val fragment = ErrorDialogFragment()
            val bundle = Bundle()
            bundle.putString(ARG_MESSAGE, message)
            fragment.setArguments(bundle)
            return fragment
        }
    }
}