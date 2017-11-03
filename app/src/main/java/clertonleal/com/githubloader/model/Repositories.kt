package clertonleal.com.githubloader.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName
import java.util.*

class Repositories : Serializable {
    @SerializedName("total_count") var totalCount: Int? = null
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null
    @SerializedName("items") var items: MutableList<Repository> = ArrayList()
}
