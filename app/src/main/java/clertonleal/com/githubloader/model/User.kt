package clertonleal.com.githubloader.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import com.google.gson.annotations.SerializedName

@Entity
class User : Serializable {
    @SerializedName("login") var login: String? = null
    @PrimaryKey @ColumnInfo(name = "user_id") @SerializedName("id") var id: Int? = null
    @SerializedName("avatar_url") var avatarUrl: String? = null
    @SerializedName("gravatar_id") var gravatarId: String? = null
    @ColumnInfo(name = "user_url") @SerializedName("url") var url: String? = null
    @ColumnInfo(name = "user_html_url") @SerializedName("html_url") var htmlUrl: String? = null
    @SerializedName("followers_url") var followersUrl: String? = null
    @SerializedName("following_url") var followingUrl: String? = null
    @SerializedName("gists_url") var gistsUrl: String? = null
    @SerializedName("starred_url") var starredUrl: String? = null
    @SerializedName("subscriptions_url") var subscriptionsUrl: String? = null
    @SerializedName("organizations_url") var organizationsUrl: String? = null
    @SerializedName("repos_url") var reposUrl: String? = null
    @ColumnInfo(name = "user_events_url") @SerializedName("events_url") var eventsUrl: String? = null
    @SerializedName("received_events_url") var receivedEventsUrl: String? = null
    @SerializedName("type") var type: String? = null
    @SerializedName("site_admin") var siteAdmin: Boolean? = null
}
