package com.startupxpand.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startupxpand.app.data.Country
import com.startupxpand.app.data.SampleData
import com.startupxpand.app.ui.components.*
import com.startupxpand.app.ui.theme.*

@Composable
fun CountriesScreen() {
    Column(modifier = Modifier.fillMaxSize().background(Navy950)) {
        SectionHeader(
            title = "کشورها و روش‌ها",
            subtitle = "مسیرهای مهاجرتی استارتاپی و جایگزین"
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(SampleData.countries) { country ->
                CountryCard(country)
            }
            item { Spacer(Modifier.height(100.dp)) }
        }
    }
}

@Composable
private fun CountryCard(country: Country) {
    var expanded by remember { mutableStateOf(false) }

    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            CountryFlagPlaceholder(country.code)
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(country.name, color = Slate100, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                Text(
                    "${country.methods.size} روش مهاجرتی",
                    color = Slate400,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Icon(
                if (expanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                contentDescription = null,
                tint = Slate400
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                Spacer(Modifier.height(16.dp))
                country.methods.forEach { method ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Navy800)
                            .padding(14.dp)
                    ) {
                        Column {
                            Text(method.title, color = Slate100, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                                method.tags.forEach { tag ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(Cyan500.copy(alpha = 0.12f))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(tag, color = Cyan400, style = MaterialTheme.typography.labelMedium, fontSize = 11.sp)
                                    }
                                }
                            }
                            if (method.requirements.isNotEmpty()) {
                                Spacer(Modifier.height(10.dp))
                                Text("نیازمندی‌ها:", color = Slate400, style = MaterialTheme.typography.labelMedium)
                                method.requirements.forEach { req ->
                                    Text("• $req", color = Slate300, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 3.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
