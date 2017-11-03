package clertonleal.com.githubloader.view

import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.res.ResourcesCompat
import android.view.MenuItem
import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.application.Application
import clertonleal.com.githubloader.databinding.PullRequestActivityBinding
import clertonleal.com.githubloader.model.PullRequest
import clertonleal.com.githubloader.service.GitHubService
import clertonleal.com.githubloader.view.interfaces.PullRequestView
import clertonleal.com.githubloader.viewModel.PullRequestListViewModel
import javax.inject.Inject

class PullRequestsActivity : BaseActivity(), PullRequestView {

    @Inject
    lateinit var gitHubService: GitHubService

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.component?.inject(this)

        val user = intent.getStringExtra(USER_KEY)
        val project = intent.getStringExtra(PROJECT_KEY)
        title = project

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val binding = DataBindingUtil.setContentView<PullRequestActivityBinding>(this, R.layout.pull_request_activity)
        binding.viewModel = PullRequestListViewModel(compositeDisposable, this, gitHubService)
        binding.viewModel?.showRepositories(user, project)
    }

    override fun openPullRequest(pullRequest: PullRequest) {
        val url = pullRequest.htmlUrl
        CustomTabsIntent.Builder()
                .setToolbarColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
                .setShowTitle(true)
                .build()
                .launchUrl(this, Uri.parse(url))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        val USER_KEY = "user_key"
        val PROJECT_KEY = "project_key"
    }
}
