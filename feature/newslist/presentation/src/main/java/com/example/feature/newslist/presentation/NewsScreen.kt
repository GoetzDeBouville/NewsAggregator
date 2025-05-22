@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature.newslist.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.domain.models.Item
import com.example.core.presentation.nav.Routes
import com.example.core.resources.R
import com.example.feature.newslist.presentation.models.Event
import com.example.feature.newslist.presentation.models.State
import com.newsapp.uikit.Categories
import com.newsapp.uikit.DescriptionHtmlText
import com.newsapp.uikit.EmptyScreen
import com.newsapp.uikit.ImageBlock
import com.newsapp.uikit.LoadingIndicator
import com.newsapp.uikit.ShareButton
import com.newsapp.uikit.TitleBlock
import com.newsapp.uikit.error.ErrorScreen

@Composable
fun NewsScreen(
    isWideDisplay: Boolean,
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    Content(
        isWideDisplay,
        navController,
        state,
        viewModel::accept
    )
}

@Composable
private fun Content(
    isWideDisplay: Boolean,
    navController: NavController,
    state: State,
    accept: (Event) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        val context = LocalContext.current
        val pullToRefreshState = rememberPullToRefreshState()

        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { accept(Event.Refresh) },
            state = pullToRefreshState,
            modifier = Modifier.padding(paddingValues),
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = state.isLoading,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    state = pullToRefreshState
                )
            }
        ) {
            when {
                state.isLoading -> {
                    LoadingIndicator()
                }

                state.errorType != null -> {
                    if (state.itemList.isNotEmpty()) {
                        LaunchedEffect(state.toastMessage) {
                            state.toastMessage?.let {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                accept(Event.ClearToast)
                            }
                        }
                        ItemList(
                            isWideDisplay = isWideDisplay,
                            itemList = state.itemList,
                            navController = navController,
                            paddingValues = PaddingValues(0.dp)
                        )
                    } else {
                        ErrorScreen(state.errorType)
                    }
                }

                state.itemList.isEmpty() -> {
                    EmptyScreen()
                }

                else -> {
                    ItemList(
                        isWideDisplay = isWideDisplay,
                        itemList = state.itemList,
                        navController = navController,
                        paddingValues = PaddingValues(0.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemList(
    isWideDisplay: Boolean,
    itemList: List<Item>,
    navController: NavController,
    paddingValues: PaddingValues,
) {
    LazyColumn(contentPadding = paddingValues) {
        items(itemList) { item ->
            AdaptiveNewsItem(
                isWideDisplay = isWideDisplay,
                navController = navController,
                item = item
            )
        }
    }
}

@Composable
private fun AdaptiveNewsItem(
    isWideDisplay: Boolean,
    navController: NavController,
    item: Item
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                navController.navigate(Routes.ArticleScreen.createRoute(item.guid))
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            if (isWideDisplay) {
                Row {
                    ImageBlock(
                        item,
                        modifier = Modifier
                            .weight(0.6f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    TitleBlock(
                        item,
                        modifier = Modifier
                            .weight(0.4f)
                            .align(Alignment.CenterVertically)
                    )
                }
            } else {
                ImageBlock(
                    item,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TitleBlock(
                    item,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            DescriptionHtmlText(
                html = item.description,
                textColor = MaterialTheme.colorScheme.onSurface
            )
            Categories(item.categories)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.pubDate,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelMedium
                )
                val context = LocalContext.current
                ShareButton(context, item.link, Modifier.weight(1f))
            }
        }
    }
}