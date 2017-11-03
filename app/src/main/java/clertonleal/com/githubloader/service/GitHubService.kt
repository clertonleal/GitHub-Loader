package clertonleal.com.githubloader.service

import clertonleal.com.githubloader.model.PullRequest
import clertonleal.com.githubloader.model.Repositories
import clertonleal.com.githubloader.model.Repository
import clertonleal.com.githubloader.network.GitHubNetwork
import clertonleal.com.githubloader.room.PullRequestDao
import clertonleal.com.githubloader.room.RepositoryDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GitHubService

@Inject
constructor(private val gitHubNetwork: GitHubNetwork,
            private val repositoryDao: RepositoryDao,
            private val pullRequestDao: PullRequestDao) {

    fun getRepositories(language: String, sort: String, page: Int): Observable<MutableList<Repository>> {
        return gitHubNetwork.getRepositories("language:" + language, sort, page)
                .map { repositories: Repositories -> repositories.items }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPullRequests(user: String, repository: String, page: Int): Observable<MutableList<PullRequest>> {
        return gitHubNetwork.getPullRequests(user, repository, page).observeOn(AndroidSchedulers.mainThread())
    }

    fun getCachedRepositories(language: String, sort: String, page: Int): Observable<MutableList<Repository>> {
        return Observable.create<MutableList<Repository>>({ subscription ->
            val repo = repositoryDao.getRepositories()
            if (!repo.isEmpty()) {
                subscription.onNext(repo)
            }

            gitHubNetwork.getRepositories("language:" + language, sort, page)
                    .map { repositories: Repositories -> repositories.items }
                    .subscribe({ repositories ->
                        repositoryDao.deleteRepositories(repositories!!)
                        repositoryDao.insertRepositories(repositories)
                        subscription.onNext(repositories)
                    }, { error ->
                        if (repo.isEmpty()) {
                            subscription.onError(error)
                        }
                    })
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun getCachedPullRequests(user: String, repository: String, page: Int): Observable<MutableList<PullRequest>> {
        return Observable.create<MutableList<PullRequest>>({ subscription ->
            val pulls = pullRequestDao.getPullRequests(user + repository)
            subscription.onNext(pulls)

            gitHubNetwork.getPullRequests(user, repository, page)
                    .map { pullRequests -> fillProjectKey(pullRequests, user, repository)}
                    .subscribe({ pullRequests ->
                        pullRequestDao.insertPullRequests(pullRequests)
                        subscription.onNext(pullRequests)
                    }, { error ->
                        if (pulls.isEmpty()) {
                            subscription.onError(error)
                        }
                    })
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    private fun fillProjectKey(pullRequests: MutableList<PullRequest>, user: String, repository: String) : MutableList<PullRequest> {
        pullRequests.forEach { it.projectKey = user + repository}
        return pullRequests
    }

}
