package clertonleal.com.githubloader.dagger.component


import clertonleal.com.githubloader.view.MainActivity
import clertonleal.com.githubloader.view.PullRequestsActivity

interface Component {
    fun inject(mainActivity: MainActivity)
    fun inject(pullRequestsActivity: PullRequestsActivity)
}
