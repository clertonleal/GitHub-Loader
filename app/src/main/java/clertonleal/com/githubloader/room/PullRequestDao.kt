package clertonleal.com.githubloader.room

import android.arch.persistence.room.*
import clertonleal.com.githubloader.model.PullRequest
import clertonleal.com.githubloader.model.Repository

@Dao
interface PullRequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPullRequests(pullRequests: MutableList<PullRequest>)

    @Query("SELECT * FROM pullrequests WHERE projectKey LIKE :projectKey ORDER BY datetime(createdAt) DESC")
    fun getPullRequests(projectKey: String) : MutableList<PullRequest>

}