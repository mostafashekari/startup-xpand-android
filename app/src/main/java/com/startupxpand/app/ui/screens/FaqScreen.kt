package com.startupxpand.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startupxpand.app.data.FaqItem
import com.startupxpand.app.data.SampleData
import com.startupxpand.app.ui.components.GlassCard
import com.startupxpand.app.ui.components.SectionHeader
import com.startupxpand.app.ui.theme.*

@Composable
fun FaqScreen() {
    Column(modifier = Modifier.fillMaxSize().background(Navy950)) {
        SectionHeader(
            title = "سوالات متداول",
            subtitle = "پاسخ شفاف به پرتکرارترین پرسش‌ها"
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(SampleData.faqs) { faq ->
                FaqCard(faq)
            }
            item { Spacer(Modifier.height(100.dp)) }
        }
    }
}

@Composable
private fun FaqCard(faq: FaqItem) {
    var expanded by remember { mutableStateOf(false) }

    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    faq.question,
                    color = Slate100,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
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
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(color = Navy700)
                Spacer(Modifier.height(12.dp))
                Text(
                    faq.answer,
                    color = Slate300,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 24.sp
                )
            }
        }
    }
}
