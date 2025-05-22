@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.feature.newslist.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.domain.models.Item
import com.example.core.presentation.nav.Routes
import com.example.core.resources.CrossIc
import com.example.core.resources.R
import com.example.core.resources.SearchIcon
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
import kotlinx.coroutines.CoroutineScope

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
    accept: (Event) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                modifier = Modifier.heightIn(max = 56.dp),
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

                        Body(
                            text = state.textInput,
                            accept = accept,
                            onTextChange = { inputText ->
                                accept(Event.SearchTextChanged(inputText))
                            },
                            isWideDisplay = isWideDisplay,
                            itemList = state.itemList,
                            navController = navController,
                            paddingValues = PaddingValues(0.dp)
                        )
                    } else {
                        ErrorScreen(state.errorType)
                    }
                }

                else -> {
                    Body(
                        text = state.textInput,
                        accept = accept,
                        onTextChange = { inputText ->
                            accept(Event.SearchTextChanged(inputText))
                        },
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
private fun Body(
    text: String = "",
    accept: (Event) -> Unit,
    onTextChange: (String) -> Unit,
    isWideDisplay: Boolean,
    itemList: List<Item>,
    navController: NavController,
    paddingValues: PaddingValues,
) {
    Column {
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        SearchTextField(
            text = text,
            accept = accept,
            onTextChange = onTextChange
        )
        if (itemList.isEmpty()) {
            EmptyScreen()
        } else {
            ItemList(
                isWideDisplay = isWideDisplay,
                itemList = itemList,
                navController = navController,
                paddingValues = paddingValues
            )
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

@Composable
private fun SearchTextField(
    text: String = "",
    accept: (Event) -> Unit,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.primary
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(8.dp),
                    imageVector = SearchIcon,
                    contentDescription = "",
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = stringResource(R.string.search_by_tag),
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                        )
                    }
                    innerTextField()
                }

                if (text.isNotEmpty()) {
                    Image(
                        imageVector = CrossIc,
                        contentDescription = stringResource(R.string.clear_icon),
                        modifier = Modifier.clickable {
                            onTextChange("")
                            accept(Event.ClearSearch)
                        }
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
    )
}