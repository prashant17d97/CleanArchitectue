package com.prashant.cleanarchitecture.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.data.model.product
import com.prashant.cleanarchitecture.ui.theme.CleanArchitectureTheme

@Composable
fun DetailScreenView(
    modifier: Modifier = Modifier,
    id: String,
    detailViewModel: DetailViewModel
) {
    val hero by detailViewModel.product.collectAsState()

    LaunchedEffect(id) {
        detailViewModel.retrieve(id)
    }
    hero?.let {
        ProductDetailScreen(
            modifier = modifier,
            product = it
        )
    }

}


@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    product: Product
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        // Product Image using Coil
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.thumbnail) // Replace with your image URL
                .crossfade(true)
                .build(),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Title and Price
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Red),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Product Rating and Reviews
        Text(
            text = "${product.rating} ⭐ (${product.reviews.size} Reviews)",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Categories (Tags)
        Row(modifier = Modifier.fillMaxWidth()) {
            product.tags.forEach { tag ->
                Surface(
                    modifier = Modifier.padding(end = 8.dp),
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Additional Product Information
        Text(
            text = "Stock: ${product.stock} available",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Warranty: ${product.warrantyInformation}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Shipping: ${product.shippingInformation}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Return Policy: ${product.returnPolicy}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductDetailScreen() {
    CleanArchitectureTheme {
        ProductDetailScreen(product = product) // Pass your product object here
    }
}
