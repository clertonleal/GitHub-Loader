package clertonleal.com.githubloader.dagger.module

import javax.inject.Singleton

import clertonleal.com.githubloader.network.GitHubNetwork
import clertonleal.com.githubloader.util.GsonProvider
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@dagger.Module
class Module {

    @Provides
    @Singleton
    internal fun provideRestAdapter(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonProvider.getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    internal fun provideMovieNetwork(restAdapter: Retrofit): GitHubNetwork {
        return restAdapter.create(GitHubNetwork::class.java)
    }

}
