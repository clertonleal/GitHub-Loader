package clertonleal.com.githubloader.network

import clertonleal.com.githubloader.model.PullRequest
import clertonleal.com.githubloader.model.Repositories
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubNetwork {

    @GET("/search/repositories")
    fun getRepositories(@Query("q") language: String, @Query("sort") sort: String, @Query("page") page: Int): Observable<Repositories>

    @GET("/repos/{user}/{repository}/pulls")
    fun getPullRequests(@Path("user") user: String, @Path("repository") repository: String, @Query("page") page: Int): Observable<MutableList<PullRequest>>

}
