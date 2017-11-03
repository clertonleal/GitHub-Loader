package clertonleal.com.githubloader.dagger

import android.content.Context
import clertonleal.com.githubloader.network.GitHubNetwork
import clertonleal.com.githubloader.util.GsonProvider
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@dagger.Module
class TestModule(private val context: Context) {

    @Provides
    internal fun provideRestAdapter(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(mockUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonProvider.getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    internal fun provideMovieNetwork(restAdapter: Retrofit): GitHubNetwork {
        return restAdapter.create(GitHubNetwork::class.java)
    }

    companion object TestModule {
        var mockUrl: String = ""
    }

}
