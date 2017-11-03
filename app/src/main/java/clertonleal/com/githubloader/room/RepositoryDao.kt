package clertonleal.com.githubloader.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import clertonleal.com.githubloader.model.Repository

@Dao
interface RepositoryDao {

    @Insert
    fun insertRepositories(repositories: MutableList<Repository>)

    @Delete
    fun deleteRepositories(repositories: MutableList<Repository>)

    @Query("SELECT * FROM repositories ORDER BY stargazersCount DESC")
    fun getRepositories() : MutableList<Repository>

}