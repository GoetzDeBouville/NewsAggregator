package com.example.feature.newslist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.domain.models.Item
import com.example.core.presentation.nav.Routes
import com.example.core.resources.IconPlaceholder
import com.example.core.resources.R
import com.newsapp.uikit.Categories
import com.newsapp.uikit.DescriptionHtmlText
import com.newsapp.uikit.LoadingIndicator
import com.newsapp.uikit.error.ErrorScreen
import com.newsapp.uikit.error.ErrorScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    isWideDisplay: Boolean,
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

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
        when {
            state.value.isLoading -> {
                LoadingIndicator()
            }

            state.value.errorType != null -> {
                ErrorScreen(state.value.errorType ?: ErrorScreenState.NOTHING_FOUND)
            }

            state.value.itemList.isEmpty() -> {
                Empty()
            }

            else -> {
                ItemList(
                    isWideDisplay = isWideDisplay,
                    itemList = state.value.itemList,
                    navController = navController,
                    paddingValues = paddingValues
                )
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
                            .clip(RoundedCornerShape(16.dp))
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
                        .clip(RoundedCornerShape(16.dp))
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

            Text(
                text = item.pubDate,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun ImageBlock(
    item: Item,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.contents.getOrNull(1)?.url)
            .crossfade(true)
            .build(),
        placeholder = rememberVectorPainter(image = IconPlaceholder),
        contentDescription = stringResource(
            R.string.image_news_description,
            item.title,
            item.contents.getOrNull(1)?.credit?.scheme ?: ""
        ),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .height(240.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
private fun TitleBlock(
    item: Item,
    modifier: Modifier = Modifier
) {
    Text(
        text = item.title,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
private fun Empty() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.empty_list),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}


@Composable
private fun NewsItem(
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.contents.getOrNull(1)?.url)
                    .crossfade(true)
                    .build(),
                placeholder = rememberVectorPainter(image = IconPlaceholder),
                contentDescription = stringResource(
                    R.string.image_news_description,
                    item.title,
                    item.contents.getOrNull(1)?.credit?.scheme ?: ""
                ),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )
            DescriptionHtmlText(
                html = item.description,
                textColor = MaterialTheme.colorScheme.onSurface
            )
            Categories(item.categories)

            Text(
                text = item.pubDate,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
