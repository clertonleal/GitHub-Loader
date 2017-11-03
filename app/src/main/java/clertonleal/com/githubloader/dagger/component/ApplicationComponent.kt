package clertonleal.com.githubloader.dagger.component

import javax.inject.Singleton

import clertonleal.com.githubloader.dagger.module.AndroidModule
import clertonleal.com.githubloader.dagger.module.Module

@Singleton
@dagger.Component(modules = arrayOf(Module::class, AndroidModule::class))
interface ApplicationComponent : Component
