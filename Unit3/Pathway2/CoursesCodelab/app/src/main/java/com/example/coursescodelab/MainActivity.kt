package com.example.coursescodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursescodelab.data.DataSource
import com.example.coursescodelab.model.Topic
import com.example.coursescodelab.ui.theme.CoursesCodelabTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesApp()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun CoursesApp() {
    val context = LocalContext.current
    CoursesCodelabTheme {
        CoursesGrid()
    }
}

@ExperimentalFoundationApi
@Composable
fun CoursesGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(DataSource.topics) { topic ->
            CourseCard(topic)
        }
    }
}


@Composable
fun CourseCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        elevation = 4.dp
    ) {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageResourceId),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = topic.numCourses.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CoursesCardPreview() {
    CoursesCodelabTheme() {
        val topic = Topic(R.string.architecture, 58, R.drawable.architecture)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CourseCard(topic = topic)
        }
    }
}