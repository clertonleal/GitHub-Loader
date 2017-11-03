package clertonleal.com.githubloader.application

import clertonleal.com.githubloader.dagger.component.Component
import clertonleal.com.githubloader.dagger.component.DaggerApplicationComponent
import clertonleal.com.githubloader.dagger.module.AndroidModule
import clertonleal.com.githubloader.dagger.module.Module

open class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        component = createComponent()
    }

    protected open fun createComponent(): Component {
        return DaggerApplicationComponent.builder()
                .module(Module())
                .androidModule(AndroidModule(this))
                .build()
    }

    companion object {
        var component: Component? = null
    }
}
