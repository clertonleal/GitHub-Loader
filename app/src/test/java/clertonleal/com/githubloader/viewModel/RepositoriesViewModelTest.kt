package clertonleal.com.githubloader.viewModel

import android.view.View
import clertonleal.com.githubloader.adapter.RepositoryAdapter
import clertonleal.com.githubloader.model.Repositories
import clertonleal.com.githubloader.service.ConnectionService
import clertonleal.com.githubloader.service.GitHubService
import clertonleal.com.githubloader.util.GsonProvider
import clertonleal.com.githubloader.view.interfaces.MainView
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import util.FileLoader

class RepositoriesViewModelTest {

    private var repositoriesViewModel: RepositoriesViewModel? = null

    @Before
    fun setUp() {
        val gson = GsonProvider.getGson()
        val repoType = object : TypeToken<Repositories>() {}.type
        val service = mock(GitHubService::class.java)
        val repositories = gson.fromJson<Repositories>(FileLoader.loadJSONFromRes("repositories.json"), repoType)
        `when`(service.getRepositories(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(Observable.just(repositories.items))

        val connectionService = mock(ConnectionService::class.java)
        `when`(connectionService.hasInternetConnection()).thenReturn(true)

        repositoriesViewModel = RepositoriesViewModel(mock(RepositoryAdapter::class.java),
                mock(CompositeDisposable::class.java),
                mock(MainView::class.java),
                service,
                connectionService)
    }

    @Test
    @Throws(Exception::class)
    fun onLoadMoreProgressShouldBeVisible() {
        repositoriesViewModel?.loadMoreRepositories(2)
        Assert.assertEquals(View.GONE, repositoriesViewModel?.loadMoreVisibility?.get())
    }


}
