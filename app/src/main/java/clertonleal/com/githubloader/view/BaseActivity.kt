package clertonleal.com.githubloader.view

import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.view.interfaces.BaseView
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity : BaseView, AppCompatActivity() {

    protected var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        unsubscribeSubscriptions()
        super.onDestroy()
    }

    protected fun unsubscribeSubscriptions() {
        compositeDisposable.dispose()
        compositeDisposable = CompositeDisposable()
    }

    override fun showRetryDialog(@StringRes title: Int, callBack : (() -> Unit)?) {
        val alert = AlertDialog.Builder(this)
                .setTitle(title)
                .setCancelable(false)

        callBack?.let {
            alert.setPositiveButton(R.string.retry) { _, _ -> callBack.invoke() }
        }

        alert.setNegativeButton(android.R.string.cancel) { _, _ -> finish() }
        alert.show()
    }

    override fun showToast(resource: Int) {
        Toast.makeText(this, resource, Toast.LENGTH_LONG).show()
    }

    override fun finishActivity() {
        finish()
    }
}
