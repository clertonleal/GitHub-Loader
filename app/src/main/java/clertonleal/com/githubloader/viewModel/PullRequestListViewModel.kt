package clertonleal.com.githubloader.viewModel

import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.adapter.PullRequestAdapter
import clertonleal.com.githubloader.service.GitHubService
import clertonleal.com.githubloader.view.PullRequestsActivity
import clertonleal.com.githubloader.view.interfaces.PullRequestView
import io.reactivex.disposables.CompositeDisposable

class PullRequestListViewModel(private val compositeDisposable: CompositeDisposable,
                               private val pullRequestView: PullRequestView,
                               private val gitHubService: GitHubService) {

    var loadingVisibility = ObservableInt(View.VISIBLE)
    private val pullRequestAdapter = PullRequestAdapter()

    val adapter: PullRequestAdapter
        get() {
            pullRequestAdapter.onClick = {item -> pullRequestView.openPullRequest(item)}
            return pullRequestAdapter
        }

    fun showRepositories(user: String, project: String) {
        showLoading()
        compositeDisposable.add(gitHubService.getCachedPullRequests(user, project, 1).subscribe({ pullRequests ->
            pullRequestAdapter.initialList(pullRequests)
            dismissLoading()
            if (pullRequests.isEmpty()) {
                pullRequestView.showRetryDialog(R.string.no_pull_requests_in_repository, null)
            }
        }) { throwable ->
            Log.e(PullRequestsActivity::class.java.simpleName, throwable.message, throwable)
            dismissLoading()
            pullRequestView.showRetryDialog(R.string.pull_request_error, {showRepositories(user, project)})
        })
    }

    private fun dismissLoading() {
        loadingVisibility.set(View.GONE)
    }

    private fun showLoading() {
        loadingVisibility.set(View.VISIBLE)
    }
}
