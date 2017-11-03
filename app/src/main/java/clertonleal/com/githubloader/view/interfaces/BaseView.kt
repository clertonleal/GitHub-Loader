package clertonleal.com.githubloader.view.interfaces

import android.support.annotation.StringRes


interface BaseView {

    fun showToast(@StringRes resource: Int)
    fun showRetryDialog(@StringRes title: Int, callBack : (() -> Unit)?)
    fun finishActivity()

}