package clertonleal.com.githubloader.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import clertonleal.com.githubloader.model.PullRequest
import clertonleal.com.githubloader.model.Repository

@Database(entities = arrayOf(Repository::class, PullRequest::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun repositoryDao() : RepositoryDao
    abstract fun pullRequestDao() : PullRequestDao

}