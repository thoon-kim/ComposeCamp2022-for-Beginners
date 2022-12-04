package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.Hero
import com.example.superheroes.model.HeroesDataSource
import com.example.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesTheme {
                SuperHeroApp()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SuperHeroPreview() {
    SuperHeroesTheme {
        SuperHeroApp()
    }
}

@Composable
fun SuperHeroApp() {
    Scaffold(
        topBar = {
            HeroTopAppBar()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(HeroesDataSource.heroes) { item ->
                HeroItem(hero = item)
            }
        }
    }
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        modifier = modifier.padding(16.dp)
            .height(72.dp)
            .clip(RoundedCornerShape(16))
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HeroInformation(hero.nameRes, hero.descriptionRes)
                Spacer(Modifier.weight(1f))
                HeroIcon(hero.imageRes)
            }
        }
    }
}

@Composable
fun HeroIcon(@DrawableRes heroIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .width(40.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(8)),
        contentScale = ContentScale.Crop,
        painter = painterResource(heroIcon),
        contentDescription = null
    )
}

@Composable
fun HeroInformation(@StringRes heroName: Int, @StringRes heroInfo: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.h3,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(heroInfo),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}