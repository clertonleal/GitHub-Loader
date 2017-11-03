package clertonleal.com.githubloader.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "pullrequests", indices = arrayOf(Index("projectKey")))
class PullRequest : Serializable {
    @SerializedName("url") var url: String? = null
    @PrimaryKey @SerializedName("id") var id: Int? = null
    @SerializedName("html_url") var htmlUrl: String? = null
    @SerializedName("diff_url") var diffUrl: String? = null
    @SerializedName("patch_url") var patchUrl: String? = null
    @SerializedName("issue_url") var issueUrl: String? = null
    @SerializedName("number") var number: Int? = null
    @SerializedName("state") var state: String? = null
    @SerializedName("locked") var locked: Boolean? = null
    @SerializedName("title") var title: String? = null
    @Embedded @SerializedName("user") var user: User? = null
    @SerializedName("body") var body: String? = null
    @SerializedName("updated_at") var updatedAt: String? = null
    @SerializedName("created_at") var createdAt: String? = null
    @SerializedName("merge_commit_sha") var mergeCommitSha: String? = null
    @SerializedName("commits_url") var commitsUrl: String? = null
    @SerializedName("review_comments_url") var reviewCommentsUrl: String? = null
    @SerializedName("review_comment_url") var reviewCommentUrl: String? = null
    @SerializedName("comments_url") var commentsUrl: String? = null
    @SerializedName("statuses_url") var statusesUrl: String? = null
    var projectKey: String = ""
}