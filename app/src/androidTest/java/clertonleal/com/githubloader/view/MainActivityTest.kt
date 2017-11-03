package clertonleal.com.githubloader.view

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.dagger.TestModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.TypeSafeMatcher
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import util.FileLoader

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
                allOf(withText("GitHub Loader"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()))
        textView.check(matches(withText("GitHub Loader")))

        val textView2 = onView(
                allOf(withId(R.id.fork_number), withText("6781"),
                        childAtPosition(
                                childAtPosition(
                                        instanceOf(android.widget.RelativeLayout::class.java),
                                        2),
                                1),
                        isDisplayed()))
        textView2.check(matches(withText("6781")))

        val textView3 = onView(
                allOf(withId(R.id.star_number), withText("19736"),
                        childAtPosition(
                                childAtPosition(
                                        instanceOf(android.widget.RelativeLayout::class.java),
                                        2),
                                3),
                        isDisplayed()))
        textView3.check(matches(withText("19736")))

        val textView4 = onView(
                allOf(withId(R.id.user_name), withText("elastic"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        1),
                                1),
                        isDisplayed()))
        textView4.check(matches(withText("elastic")))

        val textView5 = onView(
                allOf(withId(R.id.title), withText("elasticsearch"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                0),
                        isDisplayed()))
        textView5.check(matches(withText("elasticsearch")))

        val textView6 = onView(
                allOf(withId(R.id.description), withText("Open Source, Distributed, RESTful Search Engine"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                1),
                        isDisplayed()))
        textView6.check(matches(withText("Open Source, Distributed, RESTful Search Engine")))

        mockServer.enqueue(MockResponse().setResponseCode(200).setBody(loadJSONFromAsset("pull_requests.json")))

        onView(withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
                .check(matches(withText("elasticsearch")))

        val textView8 = onView(
                allOf(withId(R.id.title), withText("Start to centralize creation of XContentParser in tests"),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(R.id.list),
                                                0)),
                                0),
                        isDisplayed()))
        textView8.check(matches(withText("Start to centralize creation of XContentParser in tests")))

        val recyclerView2 = onView(
                allOf(withId(R.id.list), isDisplayed()))
        recyclerView2.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }

    companion object MainActivityTest {
        private val mockServer = MockWebServer()

        @BeforeClass
        @JvmStatic fun setup() {
            TestModule.mockUrl = mockServer.url("/").toString()
            mockServer.enqueue(MockResponse().setResponseCode(200).setBody(loadJSONFromAsset("repositories.json")))
        }

        fun loadJSONFromAsset(fileName: String): String? {
            val input = InstrumentationRegistry.getContext().assets.open(fileName)
            return FileLoader.loadJSONFromInputStream(input)
        }
    }
}
