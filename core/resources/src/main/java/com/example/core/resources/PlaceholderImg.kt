package com.example.core.resources

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val PlaceholderImg: ImageVector
    @Composable
    get() {
        _Placeholder = ImageVector.Builder(
            name = "Placeholder",
            defaultWidth = 240.dp,
            defaultHeight = 240.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(fill = SolidColor(Color(0xFF2D2220))) {
                moveTo(27.5f, 6f)
                horizontalLineToRelative(-23f)
                curveTo(3.67f, 6f, 3f, 6.67f, 3f, 7.5f)
                verticalLineToRelative(17f)
                curveTo(3f, 25.33f, 3.67f, 26f, 4.5f, 26f)
                horizontalLineToRelative(23f)
                curveToRelative(0.83f, 0f, 1.5f, -0.67f, 1.5f, -1.5f)
                verticalLineToRelative(-17f)
                curveTo(29f, 6.67f, 28.33f, 6f, 27.5f, 6f)
                close()
                moveTo(28f, 18.69f)
                curveToRelative(0f, 0f, 0f, 3.899f, 0f, 5.81f)
                curveToRelative(0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f)
                horizontalLineToRelative(-23f)
                curveTo(4.22f, 25f, 4f, 24.78f, 4f, 24.5f)
                curveToRelative(0f, -0.4f, 0f, -0.75f, 0f, -0.75f)
                curveToRelative(0f, -0.5f, 0f, -1f, 0f, -1f)
                lineToRelative(10.31f, -6.81f)
                lineToRelative(3.04f, 2.23f)
                lineToRelative(5.24f, -4.23f)
                lineTo(28f, 17.69f)
                curveTo(28f, 17.69f, 28f, 18.09f, 28f, 18.69f)
                close()
                moveTo(28f, 16.4f)
                lineToRelative(-4.84f, -3.28f)
                curveToRelative(-0.18f, -0.12f, -0.37f, -0.18f, -0.561f, -0.18f)
                curveToRelative(-0.189f, 0f, -0.39f, 0.06f, -0.56f, 0.18f)
                lineToRelative(-4.7f, 3.76f)
                lineToRelative(-2.52f, -1.78f)
                curveToRelative(-0.17f, -0.12f, -0.37f, -0.18f, -0.56f, -0.18f)
                curveToRelative(-0.19f, 0f, -0.38f, 0.06f, -0.56f, 0.18f)
                lineTo(4f, 21.54f)
                curveToRelative(0f, 0f, 0f, -13.1f, 0f, -14.04f)
                curveTo(4f, 7.22f, 4.22f, 7f, 4.5f, 7f)
                horizontalLineToRelative(23f)
                curveTo(27.78f, 7f, 28f, 7.22f, 28f, 7.5f)
                curveTo(28f, 8.06f, 28f, 16.4f, 28f, 16.4f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFD561))) {
                moveTo(13f, 11f)
                curveToRelative(0f, 0.93f, -0.64f, 1.71f, -1.5f, 1.93f)
                curveToRelative(-0.86f, -0.22f, -1.5f, -1f, -1.5f, -1.93f)
                reflectiveCurveToRelative(0.64f, -1.71f, 1.5f, -1.93f)
                curveTo(12.36f, 9.29f, 13f, 10.07f, 13f, 11f)
                close()
            }
            path(fill = SolidColor(Color(0xFF5FFFBA))) {
                moveTo(28f, 18.69f)
                curveToRelative(0f, 0f, 0f, 3.899f, 0f, 5.81f)
                curveToRelative(0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f)
                horizontalLineToRelative(-23f)
                curveTo(4.22f, 25f, 4f, 24.78f, 4f, 24.5f)
                curveToRelative(0f, -0.4f, 0f, -0.75f, 0f, -0.75f)
                lineToRelative(10.31f, -6.81f)
                lineToRelative(3.04f, 2.23f)
                lineToRelative(5.24f, -4.23f)
                lineTo(28f, 18.69f)
                close()
            }
            path(fill = SolidColor(Color(0xFF2D2220))) {
                moveTo(11f, 9f)
                curveToRelative(1.103f, 0f, 2f, 0.897f, 2f, 2f)
                reflectiveCurveToRelative(-0.897f, 2f, -2f, 2f)
                reflectiveCurveToRelative(-2f, -0.897f, -2f, -2f)
                reflectiveCurveTo(9.897f, 9f, 11f, 9f)
                moveTo(11f, 8f)
                curveToRelative(-1.657f, 0f, -3f, 1.343f, -3f, 3f)
                reflectiveCurveToRelative(1.343f, 3f, 3f, 3f)
                reflectiveCurveToRelative(3f, -1.343f, 3f, -3f)
                reflectiveCurveTo(12.657f, 8f, 11f, 8f)
                lineTo(11f, 8f)
                close()
            }
        }.build()

        return _Placeholder!!
    }

@Suppress("ObjectPropertyName")
private var _Placeholder: ImageVector? = null


@Preview
@Composable
private fun IconPlaceholder() {
    Icon(
        imageVector = PlaceholderImg,
        contentDescription = "Placeholder Icon",
        modifier = Modifier.size(64.dp)
    )
}