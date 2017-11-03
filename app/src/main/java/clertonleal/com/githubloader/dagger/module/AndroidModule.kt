package clertonleal.com.githubloader.dagger.module

import android.arch.persistence.room.Room
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import clertonleal.com.githubloader.room.Database
import clertonleal.com.githubloader.room.PullRequestDao
import clertonleal.com.githubloader.room.RepositoryDao

import dagger.Provides
import javax.inject.Singleton

@dagger.Module
class AndroidModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideConnectivityManager(): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideContentResolver(): ContentResolver {
        return context.contentResolver
    }

    @Provides
    fun provideResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : Database {
        return Room.databaseBuilder(context, Database::class.java, "db.db").build()
    }

    @Provides
    fun provideRepositoryDao(database: Database) : RepositoryDao {
        return database.repositoryDao()
    }

    @Provides
    fun providePullRequestDao(database: Database) : PullRequestDao {
        return database.pullRequestDao()
    }

}
