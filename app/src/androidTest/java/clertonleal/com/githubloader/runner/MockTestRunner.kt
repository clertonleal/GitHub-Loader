package clertonleal.com.githubloader.runner

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

import clertonleal.com.githubloader.app.TestApplication

class MockTestRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }

}
