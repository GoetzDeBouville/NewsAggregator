package com.newsapp.uikit

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.domain.models.Item
import com.example.core.resources.NoResourceImg
import com.example.core.resources.PlaceholderImg
import com.example.core.resources.R

@Composable
fun Categories(categories: List<String>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            CategoryChip(category)
        }
    }
}

@Composable
private fun CategoryChip(category: String) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = category,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}


@Composable
fun ImageBlock(
    item: Item,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.imageUrl)
            .crossfade(true)
            .build(),
        placeholder = rememberVectorPainter(image = PlaceholderImg),
        contentDescription = stringResource(
            com.example.core.resources.R.string.image_news_description,
            item.title
        ),
        error = rememberVectorPainter(image = NoResourceImg),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .height(240.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun TitleBlock(
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
fun EmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(com.example.core.resources.R.string.empty_list),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}


@Composable
fun ShareButton(
    context: Context,
    link: String,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, link)
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.getString(com.example.core.resources.R.string.share_link)
                )
            )
        }
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = context.getString(R.string.share_icon),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}