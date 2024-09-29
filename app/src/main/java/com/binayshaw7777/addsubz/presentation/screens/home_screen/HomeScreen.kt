package com.binayshaw7777.addsubz.presentation.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binayshaw7777.addsubz.domain.model.MainScreenItems
import com.binayshaw7777.addsubz.presentation.navigation.LocalNavHost
import com.binayshaw7777.addsubz.presentation.navigation.Screens

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val navController = LocalNavHost.current

    val mainScreenItems by remember {
        mutableStateOf(MainScreenItems.entries)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hi Binay.",
                style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.SemiBold),
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.SettingScreen.name)
                })
        }
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(mainScreenItems) { item ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = item.displayName,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        headlineColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            when (item) {
                                MainScreenItems.SHOPPING_CALCULATOR -> navController.navigate(Screens.ShoppingCalculatorScreen.name)
                                else -> {}
                            }
                        }
                )
            }
        }
    }
}