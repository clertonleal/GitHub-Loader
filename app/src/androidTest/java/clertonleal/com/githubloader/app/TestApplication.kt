package clertonleal.com.githubloader.app

import clertonleal.com.githubloader.application.Application
import clertonleal.com.githubloader.dagger.DaggerTestComponent
import clertonleal.com.githubloader.dagger.TestModule
import clertonleal.com.githubloader.dagger.component.Component
import clertonleal.com.githubloader.dagger.module.AndroidModule

class TestApplication : Application() {

    override fun createComponent(): Component {
        return DaggerTestComponent.builder()
                .androidModule(AndroidModule(this))
                .testModule(TestModule(this))
                .build()
    }
}
