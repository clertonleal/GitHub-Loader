package clertonleal.com.githubloader.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.adapter.RepositoryAdapter
import clertonleal.com.githubloader.application.Application
import clertonleal.com.githubloader.databinding.ActivityMainBinding
import clertonleal.com.githubloader.model.Repository
import clertonleal.com.githubloader.service.ConnectionService
import clertonleal.com.githubloader.service.GitHubService
import clertonleal.com.githubloader.view.interfaces.MainView
import clertonleal.com.githubloader.viewModel.RepositoriesViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var gitHubService: GitHubService

    @Inject
    lateinit var connectionService: ConnectionService

    private var viewModel: RepositoriesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.component?.inject(this)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = RepositoriesViewModel(RepositoryAdapter(), compositeDisposable, this, gitHubService, connectionService)
        binding.viewModel = viewModel
        viewModel?.showRepositories()
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribeSubscriptions()
    }

    override fun openRepository(repository: Repository) {
        val intent = Intent(this, PullRequestsActivity::class.java)
        intent.putExtra(PullRequestsActivity.USER_KEY, repository.owner?.login)
        intent.putExtra(PullRequestsActivity.PROJECT_KEY, repository.name)
        startActivity(intent)
    }

}
