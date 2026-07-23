package com.startupxpand.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FormatQuote
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startupxpand.app.data.SampleData
import com.startupxpand.app.data.SuccessStory
import com.startupxpand.app.ui.components.GlassCard
import com.startupxpand.app.ui.components.SectionHeader
import com.startupxpand.app.ui.theme.*

@Composable
fun StoriesScreen() {
    Column(modifier = Modifier.fillMaxSize().background(Navy950)) {
        SectionHeader(
            title = "روایت‌های موفقیت",
            subtitle = "داستان واقعی کسانی که مسیر را طی کردند"
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(SampleData.stories) { story ->
                StoryCard(story)
            }
            item { Spacer(Modifier.height(100.dp)) }
        }
    }
}

@Composable
private fun StoryCard(story: SuccessStory) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(listOf(Cyan500, Amber500))
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    story.name.take(1),
                    color = Navy950,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(Modifier.width(14.dp))
            Column {
                Text(story.name, color = Slate100, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text("${story.country} • ${story.industry}", color = Slate400, style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(Modifier.height(14.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            InfoPill(Icons.Rounded.Schedule, story.duration)
            InfoPill(null, story.project)
        }

        Spacer(Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Navy800)
                .padding(16.dp)
        ) {
            Column {
                Icon(Icons.Rounded.FormatQuote, null, tint = Cyan400.copy(alpha = 0.5f), modifier = Modifier.size(28.dp))
                Spacer(Modifier.height(6.dp))
                Text(
                    story.testimonial,
                    color = Slate200,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 24.sp
                )
            }
        }

        Spacer(Modifier.height(10.dp))
        Text(story.description, color = Slate400, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun InfoPill(icon: androidx.compose.ui.graphics.vector.ImageVector?, text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Cyan500.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(icon, null, tint = Cyan400, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(4.dp))
            }
            Text(text, color = Cyan400, style = MaterialTheme.typography.labelMedium, fontSize = 11.sp)
        }
    }
}
