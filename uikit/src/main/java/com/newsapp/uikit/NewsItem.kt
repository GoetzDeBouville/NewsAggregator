package com.newsapp.uikit

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.domain.models.Category
import com.example.core.domain.models.Content
import com.example.core.domain.models.Credit
import com.example.core.domain.models.Item
import com.example.core.resources.IconPlaceholder
import com.example.core.resources.R

@Composable
fun NewsItem(item: Item) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {},
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (item.contents.size > 2) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.contents[1])
                        .crossfade(true)
                        .build(),
                    placeholder = rememberVectorPainter(image = IconPlaceholder),
                    contentDescription = stringResource(
                        R.string.image_news_description,
                        item.title,
                        item.contents[1].credit?.scheme ?: ""
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = item.title,
                    color = MaterialTheme.colorScheme.onBackground
                )
                HtmlText(html = item.description)
                Categories(item.categories)

                Text(
                    text = item.dcDate,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
private fun HtmlText(
    html: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.padding(top = 8.dp),
        factory = { context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
                autoLinkMask = Linkify.WEB_URLS
            }
        },
        update = { textView ->
            textView.text = HtmlCompat.fromHtml(
                html,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }
    )
}

@Composable
private fun Categories(categories: List<Category>) {
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
private fun CategoryChip(category: Category) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = category.value,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    val categories = listOf<Category>(
        Category("https://www.theguardian.com/society/health", "Health"),
        Category("https://www.theguardian.com/lifeandstyle/women", "Women"),
        Category("https://www.theguardian.com/lifeandstyle/sex", "Childbirth"),
        Category("https://www.theguardian.com/lifeandstyle/sex", "Sex"),
        Category("https://www.theguardian.com/lifeandstyle/sex", "Society"),
        Category("https://www.theguardian.com/lifeandstyle/sex", "Life and style"),
    )
    val content = Content(
        type = "netus",
        width = "aliquet",
        url = "https://i.guim.co.uk/img/media/964e0a68cad1e2d3a8f83bd03a43a25e65f9cebb/96_0_1349_1080/master/1349.jpg?width=460&quality=85&auto=format&fit=max&s=f1bcd376f313a83cb97c1782dbebc0bb",
        credit = Credit(scheme = "Photograph: HBO", value = "conubia")
    )
    NewsItem(
        item = Item(
            title = "Plunging value and a content cliff edge: what’s gone wrong at Sky?",
            link = "nullam",
            description = "<p>Since Comcast takeover, broadcaster has slashed jobs and is losing the exclusive shows that drew subscribers</p><p>When the boss of the media multinational Comcast was putting together an <a href=\"https://www.theguardian.com/media/2018/sep/23/comcast-30bn-bid-for-sky-what-does-it-mean#:~:text=Why%20has%20Comcast%20bid%20%C2%A3,and%20produces%20its%20own%20content.https://www.theguardian.com/business/2018/feb/27/comcast-boss-brian-roberts-london-cab-driver-role-sky-bid\">ultimately eye-watering £31bn bid for Sky</a>, he recounted how a <a href=\"https://www.theguardian.com/business/2018/feb/27/comcast-boss-brian-roberts-london-cab-driver-role-sky-bid\">chat with a London cab driver</a> reinforced his opinion that he was in pursuit of a crown jewel of UK broadcasting.</p><p>Brian Roberts’s plan was to use Sky to build an international powerhouse outside the US – after being beaten by Disney in the <a href=\"https://www.theguardian.com/film/2019/mar/20/disney-seals-71bn-deal-for-21st-century-fox-as-it-prepares-to-take-on-netflix\">battle to acquire his prime target</a>, Rupert Murdoch’s 21st Century Fox – but some analysts and industry figures wonder if he has been taken for a very expensive ride.</p> <a href=\"https://www.theguardian.com/business/2025/may/19/sky-comcast-jobs-exclusive-shows-subscribers\">Continue reading...</a>",
            categories = categories,
            pubDate = "hac",
            guid = "https://www.theguardian.com/society/2025/may/18/the-private-pain-of-prolapse-six-things-i-wish-id-known-from-sex-to-exercise-to-mental-health",
            contents = listOf(content, content),
            dcCreator = "Rory Carroll",
            dcDate = "Mon, 19 May 2025 05:00:54 GMT"
        )
    )
}