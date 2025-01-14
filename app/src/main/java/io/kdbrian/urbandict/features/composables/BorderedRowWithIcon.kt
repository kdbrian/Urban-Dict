package io.kdbrian.urbandict.features.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.util.Shapes

@Composable
fun BorderedRowWithIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    color: Color = Color.Black,
    borderColor: Color = Color.LightGray,
) {
    Surface(
        shape = Shapes.rounded6Dp,
        border = BorderStroke(width = 1.dp, color = borderColor),
        modifier = modifier.padding(horizontal = 12.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = title,
                modifier = Modifier.size(45.dp)
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text = title,
                style = TextStyle(fontFamily = gambarino, fontSize = 16.sp, color = color)
            )

        }
    }
}