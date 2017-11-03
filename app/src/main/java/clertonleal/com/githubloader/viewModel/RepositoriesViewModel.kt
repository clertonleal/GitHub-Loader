package clertonleal.com.githubloader.viewModel

import android.databinding.ObservableInt
import android.util.Log
import android.view.View

import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.adapter.RepositoryAdapter
import clertonleal.com.githubloader.model.Repository
import clertonleal.com.githubloader.service.ConnectionService
import clertonleal.com.githubloader.service.GitHubService
import clertonleal.com.githubloader.view.MainActivity
import clertonleal.com.githubloader.view.interfaces.MainView
import io.reactivex.disposables.CompositeDisposable

class RepositoriesViewModel(private var repositoryAdapter: RepositoryAdapter,
                            private val compositeDisposable: CompositeDisposable,
                            private val mainView: MainView,
                            private val gitHubService: GitHubService,
                            private val connectionService: ConnectionService) {

    var loadMoreVisibility = ObservableInt(View.GONE)
    var loadingVisibility = ObservableInt(View.VISIBLE)

    val adapter: RepositoryAdapter
        get() {
            repositoryAdapter.onClick = {item -> mainView.openRepository(item)}
            repositoryAdapter.onLoadMore = {page -> loadMoreRepositories(page)}
            return repositoryAdapter
        }

    fun showRepositories() {
        showLoading()
        compositeDisposable.addAll(gitHubService.getCachedRepositories("Java", "stars", 1).subscribe({ repositories ->
            setRepositories(repositories)
            dismissLoading()
        }) { throwable ->
            Log.e(MainActivity::class.java.simpleName, throwable.message, throwable)
            dismissLoading()
            if (repositoryAdapter.list.isEmpty()) {
                mainView.showRetryDialog(R.string.initialization_error, {showRepositories()})
            }
        })
    }

    private fun dismissLoading() {
        loadingVisibility.set(View.GONE)
    }

    private fun showLoading() {
        loadingVisibility.set(View.VISIBLE)
    }

    fun loadMoreRepositories(page: Int) {
        loadMoreVisibility.set(View.VISIBLE)
        if (connectionService.hasInternetConnection()) {
            compositeDisposable.addAll(gitHubService.getRepositories("Java", "stars", page)
                    .subscribe({ result -> addMoreRepositories(result) })
                    { throwable ->
                        Log.e(MainActivity::class.java.simpleName, throwable.message, throwable)
                        errorInAddMore()
                        mainView.showToast(R.string.error_load_more)
                    })
        } else {
            errorInAddMore()
            mainView.showToast(R.string.no_internet)
        }
    }

    private fun addMoreRepositories(repositories: MutableList<Repository>) {
        repositoryAdapter.addList(repositories)
        loadMoreVisibility.set(View.GONE)
    }

    private fun setRepositories(repositories: MutableList<Repository>) {
        repositoryAdapter.initialList(repositories)
        loadMoreVisibility.set(View.GONE)
    }

    private fun errorInAddMore() {
        loadMoreVisibility.set(View.GONE)
    }
}
