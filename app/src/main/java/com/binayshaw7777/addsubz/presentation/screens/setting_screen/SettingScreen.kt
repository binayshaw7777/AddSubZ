package com.binayshaw7777.addsubz.presentation.screens.setting_screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.binayshaw7777.addsubz.R
import com.binayshaw7777.addsubz.presentation.components.ThemeDialog
import com.binayshaw7777.addsubz.presentation.navigation.Screens

@Composable
fun SettingScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    var showThemeDialog by remember {
        mutableStateOf(false)
    }

    if (showThemeDialog) {
         ThemeDialog(onDismissRequest = { showThemeDialog = false })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 20.dp)
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Setting.",
                style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.SemiBold),
            )
        }
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        LazyColumn {
            item {
                ListItem(
                    headlineContent = {
                        Text(text = "Theme")
                    },
                    leadingContent = { Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_theme), contentDescription = null) },
                    modifier = Modifier.clickable { showThemeDialog = true }
                )
            }
        }
    }
}