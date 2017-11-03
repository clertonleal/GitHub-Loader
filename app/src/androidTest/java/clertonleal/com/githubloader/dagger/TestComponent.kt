package clertonleal.com.githubloader.dagger

import javax.inject.Singleton

import clertonleal.com.githubloader.dagger.component.Component
import clertonleal.com.githubloader.dagger.module.AndroidModule

@Singleton
@dagger.Component(modules = arrayOf(TestModule::class, AndroidModule::class))
interface TestComponent : Component
