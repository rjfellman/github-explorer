package com.example.githubexplorer

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class InterfaceTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testList() {
        val view = composeTestRule.onNode(hasTestTag("listHolder"), useUnmergedTree = true)
        view.assertIsDisplayed()
    }

    @Test
    fun testSearch() {
        val view = composeTestRule.onNode(hasTestTag("searchContainer"), useUnmergedTree = true)
        view.assertIsDisplayed()
        val search = composeTestRule.onNode(hasText("nytimes"))
        search.assertIsDisplayed()
        search.performTextReplacement("square")
        search.assertIsDisplayed()

        val list = composeTestRule.onNode(hasTestTag("listHolder"), useUnmergedTree = true)
        list.assertIsDisplayed()
        val entry = composeTestRule.onNode(hasText("retrofit"))
        Thread.sleep(2000) //wait for API call to succeed
        entry.assertIsDisplayed()
    }
}