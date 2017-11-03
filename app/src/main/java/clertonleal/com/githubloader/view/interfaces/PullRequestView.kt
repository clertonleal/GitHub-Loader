package clertonleal.com.githubloader.view.interfaces


import clertonleal.com.githubloader.model.PullRequest

interface PullRequestView : BaseView {

    fun openPullRequest(pullRequest: PullRequest)

}
