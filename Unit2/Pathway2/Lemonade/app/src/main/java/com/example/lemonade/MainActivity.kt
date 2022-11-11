package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

@Preview
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    val descResource = when(currentStep) {
        1 -> R.string.lemonade_desc_1
        2 -> R.string.lemonade_desc_2
        3 -> R.string.lemonade_desc_3
        else -> R.string.lemonade_desc_4
    }
    val imageResource = when(currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val titleResource = when(currentStep) {
        1 -> R.string.lemonade_title_1
        2 -> R.string.lemonade_title_2
        3 -> R.string.lemonade_title_3
        else -> R.string.lemonade_title_4
    }
    var count by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LemonTextAndImage(
            descReourceId = descResource,
            imageResourceId = imageResource,
            titleResourceId = titleResource,
            onClick = {
                when (currentStep) {
                    1 -> {
                        currentStep = 2
                        count = (2..4).random()
                    }
                    2 -> {
                        if (--count == 0)
                            currentStep = 3
                    }
                    3 -> currentStep = 4
                    4 -> currentStep = 1
                }
            }
        )
    }
}

@Composable
fun LemonTextAndImage(
    descReourceId: Int,
    imageResourceId: Int,
    titleResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = descReourceId),
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = titleResourceId),
            modifier = Modifier
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(
                    onClick = onClick
                )
        )
    }
}