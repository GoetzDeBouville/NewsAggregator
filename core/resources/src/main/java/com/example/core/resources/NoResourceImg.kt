package com.example.core.resources

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NoResourceImg: ImageVector
    @Composable
    get() {
        _NoResourceImg = ImageVector.Builder(
            name = "NoImage",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 100f,
            viewportHeight = 100f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFEEEEEE)),
                stroke = SolidColor(Color(0xFF999999)),
                strokeLineWidth = 1f
            ) {
                moveTo(2f, 23f)
                lineTo(77f, 6f)
                lineTo(94f, 75f)
                lineTo(18f, 94f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFCCCCCC),
                        0.7f to Color(0xFF000000)
                    ),
                    start = Offset(50f, 40f),
                    end = Offset(50f, 100f)
                ),
                stroke = SolidColor(Color(0xFF444444)),
                strokeLineWidth = 1f
            ) {
                moveTo(8f, 27f)
                lineTo(73f, 12f)
                lineTo(88f, 71f)
                lineTo(22f, 88f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFEEEEEE)),
                stroke = SolidColor(Color(0xFF999999)),
                strokeLineWidth = 1f
            ) {
                moveToRelative(19f, 12f)
                lineToRelative(77f, 0f)
                lineToRelative(0f, 74f)
                lineToRelative(-77f, 0f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.2f to Color(0xFF301D00),
                        1f to Color(0xFFFFAB00)
                    ),
                    start = Offset(50f, 0f),
                    end = Offset(50f, 60f)
                ),
                stroke = SolidColor(Color(0xFF444444)),
                strokeLineWidth = 1f
            ) {
                moveToRelative(25f, 18f)
                lineToRelative(65f, 0f)
                lineToRelative(0f, 62f)
                lineToRelative(-65f, 0f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFCCCCCC),
                        0.7f to Color(0xFF000000)
                    ),
                    start = Offset(50f, 40f),
                    end = Offset(50f, 100f)
                ),
                stroke = SolidColor(Color(0xFF666666)),
                strokeLineWidth = 1f
            ) {
                moveToRelative(25f, 48f)
                lineToRelative(0f, 32f)
                lineToRelative(65f, 0f)
                lineToRelative(0f, -45f)
                curveTo(90f, 35f, 84f, 51f, 77f, 54f)
                curveTo(68f, 58f, 64f, 52f, 61f, 46f)
                curveTo(57f, 54f, 56f, 58f, 50f, 60f)
                curveTo(44f, 62f, 38f, 45f, 36f, 38f)
                curveTo(32f, 43f, 29f, 47f, 25f, 48f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFF3D3D)),
                fillAlpha = 0.6f,
                stroke = SolidColor(Color(0xFF730000)),
                strokeLineWidth = 2f
            ) {
                moveTo(53f, 44f)
                lineTo(44f, 53f)
                lineTo(61f, 71f)
                lineTo(44f, 88f)
                lineTo(53f, 97f)
                lineTo(71f, 80f)
                lineTo(88f, 97f)
                lineTo(96f, 88f)
                lineTo(80f, 71f)
                lineTo(97f, 52f)
                lineTo(89f, 44f)
                lineTo(71f, 61f)
                close()
            }
        }.build()
        return _NoResourceImg!!
    }

@Suppress("ObjectPropertyName")
private var _NoResourceImg: ImageVector? = null


@Preview
@Composable
private fun ShowImg() {
    MaterialTheme {
        Image(
            imageVector = NoResourceImg,
            contentDescription = null,
        )
    }
}