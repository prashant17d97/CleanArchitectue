package com.prashant.cleanarchitecture.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.data.model.product
import com.prashant.cleanarchitecture.ui.theme.CleanArchitectureTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    onClick: (id: String) -> Unit = {}
) {
    val products by homeViewModel.products.collectAsState()
    val isRefreshing by homeViewModel.isRefreshing.collectAsState()
    val pullToRefreshState = rememberPullToRefreshState()

    Box {
        HomeView(
            modifier = modifier.pullToRefresh(
                isRefreshing = isRefreshing,
                state = pullToRefreshState,
                onRefresh = {
                    homeViewModel.fetchSuperHeroes(refreshRequest = true)
                }
            ),
            products = products,
            onClick = onClick
        )
        Indicator(
            modifier = Modifier.align(Alignment.TopCenter),
            isRefreshing = isRefreshing,
            state = pullToRefreshState
        )
    }
}

@Composable
private fun HomeView(modifier: Modifier, products: List<Product>, onClick: (String) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.Top
        )
    ) {
        items(
            items = products,
            key = { it.id }) { product ->
            HerCard(
                modifier = Modifier.clickable(onClick = { onClick(product.id.toString()) }),
                product = product
            )
        }
    }
}

@Composable
fun HerCard(
    modifier: Modifier = Modifier,
    product: Product,
) {
    Card(modifier=modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.Start
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail) // Replace with your image URL
                    .crossfade(true)
                    .build(),
                contentDescription = "Product Image",
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(16.dp)),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    product.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(product.description.split(" ").take(10).joinToString(" "), style = MaterialTheme.typography.bodyMedium)
                Text("$${product.price}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
        }

    }
}

@Preview
@Composable
private fun HeroPrev() {
    CleanArchitectureTheme {
        HerCard(product = product)
    }
}