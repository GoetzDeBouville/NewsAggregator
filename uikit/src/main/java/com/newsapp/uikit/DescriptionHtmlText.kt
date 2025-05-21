package com.newsapp.uikit

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun DescriptionHtmlText(
    html: String,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    val textColor = textColor.toArgb()

    AndroidView(
        modifier = modifier.padding(top = 8.dp),
        factory = { context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
                autoLinkMask = Linkify.WEB_URLS
                setTextColor(textColor)
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
