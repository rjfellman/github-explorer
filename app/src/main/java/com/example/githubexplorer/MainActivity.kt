package com.example.githubexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubexplorer.presentation.repo_list.RepoListView
import com.example.githubexplorer.ui.theme.GithubExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubExplorerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .testTag("listHolder"),
                    color = MaterialTheme.colors.background) {
                    var navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "list"
                    ) {
                            composable(
                                route = "list"
                            ) {
                                RepoListView(navController)
                            }
                        }
                }
            }
        }
    }
}