package github.paulmburu.githublite

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import github.paulmburu.githublite.ui.MainActivity
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import io.github.kakaocup.kakao.toolbar.KToolbar
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class MainScreen : Screen<MainScreen>() {
    val toolbar = KToolbar { withId(R.id.toolbar) }
    val searchImageView = KImageView { withId(R.id.search_image_view) }
    val nothingToSeeTextView = KTextView { withId(R.id.nothing_to_see) }
}

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val screen = MainScreen()


    @Test
    fun empty_state_views_displayed_on_app_launch() {
        screen {
            toolbar.isVisible()
            searchImageView.isVisible()
            nothingToSeeTextView.hasText(R.string.nothing_to_see_here)
        }
    }
}