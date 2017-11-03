package clertonleal.com.githubloader.view.interfaces

import clertonleal.com.githubloader.model.Repository

interface MainView : BaseView {

    fun openRepository(repository: Repository)

}
