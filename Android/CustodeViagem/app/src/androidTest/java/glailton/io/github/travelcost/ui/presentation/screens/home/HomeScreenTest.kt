package glailton.io.github.travelcost.ui.presentation.screens.home

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers.withText
import glailton.io.github.travelcost.R
import glailton.io.github.travelcost.rule.WebServerRule
import glailton.io.github.travelcost.rule.mockTravelCostResponse
import glailton.io.github.travelcost.rule.waitUntil
import glailton.io.github.travelcost.ui.presentation.MainActivity
import glailton.io.github.travelcost.ui.theme.TravelCostTheme
import io.github.kakaocup.kakao.common.utilities.getResourceString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.compose.getViewModel

class HomeScreenTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @ExperimentalFoundationApi
    private lateinit var scenario: ActivityScenario<MainActivity>

    private val intent = Intent(
        context,
        MainActivity::class.java
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val webServerRule = WebServerRule()

    @Test
    fun should_see_all_items_on_home_screen() {
        composeTestRule.setContent {
            TravelCostTheme {
                HomeScreen(vm = getViewModel())
            }
        }

        composeTestRule.run {
            onNodeWithText(getResourceString(R.string.distance))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.total_kilometers))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.price))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.price_per_liter))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.autonomy))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.kilometers_per_liter))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.total_expense))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.total).replace("%s", "0"))
                .assertIsDisplayed()
            onNodeWithText(getResourceString(R.string.calculate))
                .assertIsDisplayed()
        }
    }

    @Test
    fun should_show_response() {
        webServerRule.mockTravelCostResponse()

        composeTestRule.setContent {
            TravelCostTheme {
                HomeScreen(vm = getViewModel())
            }
        }

        composeTestRule.run {
            onNodeWithText(getResourceString(R.string.distance))
                .performTextInput("200")

            onNodeWithText(getResourceString(R.string.price))
                .performTextInput("5")

            onNodeWithText(getResourceString(R.string.autonomy))
                .performTextInput("10")

            onNodeWithText(getResourceString(R.string.total).replace("%s", "0"))
                .assertIsDisplayed()

            onNodeWithText(getResourceString(R.string.calculate))
                .assertIsDisplayed()
                .performClick()

            SystemClock.sleep(1000)

            onNodeWithText(getResourceString(R.string.total).replace("%s", "20.0"))
                .assertIsDisplayed()
        }
    }

    @Test
    fun should_see_toast_error_on_home_screen() {
        composeTestRule.setContent {
            TravelCostTheme {
                HomeScreen(vm = getViewModel())
            }
        }

        composeTestRule.run {

            onNodeWithText(getResourceString(R.string.calculate))
                .assertIsDisplayed()
                .performClick()
        }
    }
}