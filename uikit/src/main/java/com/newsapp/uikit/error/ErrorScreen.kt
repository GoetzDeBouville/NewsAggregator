package com.newsapp.uikit.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ErrorScreen(errorType: ErrorScreenState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val description = stringResource(errorType.errorDescriptionResId)
        Column {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                imageVector = errorType.errorImg.invoke(),
                contentDescription = description,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = description,
                style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun ShowErrorScreen() {
    MaterialTheme {
        ErrorScreen(ErrorScreenState.SERVER_ERROR)
    }
}
