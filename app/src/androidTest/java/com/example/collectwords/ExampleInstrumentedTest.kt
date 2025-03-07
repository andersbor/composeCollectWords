package com.example.collectwords

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule val rule = createComposeRule()

    @Test fun wordsTest() {
        rule.setContent {
            CollectWords()
        }
        rule.onNodeWithText("Enter a word").performTextClearance()
        rule.onNodeWithText("Enter a word").performTextInput("hello")
        rule.onNodeWithText("Add").performClick()

        rule.onNodeWithText("Enter a word").apply {
            performTextClearance()
            performTextInput("world")
        }
        rule.onNodeWithText("Add").performClick()

        rule.onNodeWithText("Show").performClick()

        rule.onNodeWithText("hello, world").assertExists()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.collectwords", appContext.packageName)
    }
}