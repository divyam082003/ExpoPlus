package com.gd.expoplus.ui.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun ExpenseLineChart(
    values: List<Float>,
    labels: List<String>
) {

    val maxValue = values.maxOrNull() ?: 0f

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF1E1E1E),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(16.dp)
    )
    {
        Canvas(

            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {

            val spacing = size.width / (values.size - 1)

            val strokePath = Path()

            val fillPath = Path()

            values.forEachIndexed { index, value ->

                val x = spacing * index

                val y =
                    size.height -
                            (value / maxValue) * size.height

                if (index == 0) {

                    strokePath.moveTo(x, y)

                    fillPath.moveTo(x, y)
                }
                else {

                    val previousX =
                        spacing * (index - 1)

                    val previousY =
                        size.height -
                                (values[index - 1] / maxValue) * size.height

                    val controlX =
                        (previousX + x) / 2

                    strokePath.cubicTo(
                        controlX,
                        previousY,
                        controlX,
                        y,
                        x,
                        y
                    )

                    fillPath.cubicTo(
                        controlX,
                        previousY,
                        controlX,
                        y,
                        x,
                        y
                    )
                }
            }

            fillPath.lineTo(size.width, size.height)

            fillPath.lineTo(0f, size.height)

            fillPath.close()

            drawPath(

                path = fillPath,

                brush = Brush.verticalGradient(

                    colors = listOf(
                        Color(0xFF63E65C).copy(alpha = 0.35f),
                        Color.Transparent
                    )
                )
            )

            drawPath(

                path = strokePath,

                color = Color(0xFF63E65C),

                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round
                )
            )

            values.forEachIndexed { index, value ->

                val x = spacing * index

                val y =
                    size.height -
                            (value / maxValue) * size.height

                drawCircle(

                    color = Color(0xFF63E65C),

                    radius = 7f,

                    center = Offset(x, y)
                )

                drawCircle(

                    color = Color.White,

                    radius = 3f,

                    center = Offset(x, y)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            labels.forEach { label ->

                Text(
                    text = label,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}