package com.startupxpand.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startupxpand.app.data.ReadyIdea
import com.startupxpand.app.data.SampleData
import com.startupxpand.app.ui.components.*
import com.startupxpand.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdeasScreen(onBack: () -> Unit = {}) {
    var selected by remember { mutableStateOf<ReadyIdea?>(null) }

    if (selected != null) {
        IdeaDetailSheet(idea = selected!!, onDismiss = { selected = null })
        return
    }

    Column(modifier = Modifier.fillMaxSize().background(Navy950)) {
        TopAppBar(
            title = {
                Column {
                    Text("ایده‌های آماده", fontWeight = FontWeight.Black, color = Slate100)
                    Text("بانک ایده اعتبارسنجی‌شده", style = MaterialTheme.typography.bodySmall, color = Slate400)
                }
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Rounded.ArrowBack, null, tint = Slate100)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Navy900)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(SampleData.ideas) { idea ->
                IdeaFullCard(idea = idea, onClick = { selected = idea })
            }
            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

@Composable
private fun IdeaFullCard(idea: ReadyIdea, onClick: () -> Unit) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoIndication(onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Cyan500.copy(alpha = 0.12f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(idea.category, color = Cyan400, style = MaterialTheme.typography.labelMedium)
            }
            StatusChip(idea.status, idea.statusText)
        }

        Spacer(Modifier.height(12.dp))
        Text(idea.title, color = Slate100, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
        Spacer(Modifier.height(8.dp))
        Text(idea.description, color = Slate400, style = MaterialTheme.typography.bodyMedium, lineHeight = 22.sp)
        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            idea.techStack.take(4).forEach { TechChip(it) }
        }

        Spacer(Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(idea.seatsText, color = Slate500, style = MaterialTheme.typography.bodySmall)
            Text(
                idea.primaryBtnText,
                color = if (idea.status == "full") Rose400 else Cyan400,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IdeaDetailSheet(idea: ReadyIdea, onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Navy950)
    ) {
        TopAppBar(
            title = { Text("جزئیات ایده", fontWeight = FontWeight.Bold, color = Slate100) },
            navigationIcon = {
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Rounded.ArrowBack, null, tint = Slate100)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Navy900)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            StatusChip(idea.status, idea.statusText)
            Spacer(Modifier.height(16.dp))
            Text(idea.title, style = MaterialTheme.typography.headlineLarge, color = Slate100, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(8.dp))
            Text(idea.category, color = Cyan400, style = MaterialTheme.typography.labelLarge)

            Spacer(Modifier.height(20.dp))
            GlassCard {
                Text("توضیح کامل", color = Slate100, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(idea.detailedDesc, color = Slate300, style = MaterialTheme.typography.bodyMedium, lineHeight = 24.sp)
            }

            Spacer(Modifier.height(12.dp))
            GlassCard {
                Text("پتانسیل بازار", color = Slate100, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(idea.marketPotential, color = Slate300, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(Modifier.height(12.dp))
            GlassCard {
                Text("Tech Stack", color = Slate100, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    idea.techStack.forEach { TechChip(it) }
                }
            }

            Spacer(Modifier.weight(1f))
            GradientButton(
                text = idea.primaryBtnText,
                onClick = { /* reserve */ },
                icon = Icons.Rounded.RocketLaunch,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

private fun Modifier.clickableNoIndication(onClick: () -> Unit): Modifier =
    this.clickable(onClick = onClick)
