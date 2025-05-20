package com.newsapp.uikit

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.core.domain.models.Category

@Composable
fun HtmlText(
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
fun Categories(categories: List<Category>) {
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
