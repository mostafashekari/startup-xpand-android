package com.startupxpand.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startupxpand.app.data.SampleData
import com.startupxpand.app.ui.components.*
import com.startupxpand.app.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateIdeas: () -> Unit,
    onNavigateStories: () -> Unit,
    onNavigateAssessment: () -> Unit
) {
    val hero = SampleData.hero
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Navy950)
            .verticalScroll(scroll)
    ) {
        // ─── HERO ───
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(Navy900, Navy950)
                    )
                )
                .padding(bottom = 24.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 28.dp)) {
                // Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Cyan500.copy(alpha = 0.12f))
                        .border(1.dp, Cyan500.copy(alpha = 0.3f), RoundedCornerShape(50))
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = hero.badge,
                        color = Cyan400,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(20.dp))

                Text(
                    text = hero.titleLine1,
                    style = MaterialTheme.typography.displayMedium,
                    color = Amber400,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = hero.titleLine2,
                    style = MaterialTheme.typography.displayMedium,
                    color = Slate100,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = hero.titleLine3,
                    style = MaterialTheme.typography.titleMedium,
                    color = Cyan400,
                    modifier = Modifier.padding(top = 6.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = hero.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Slate300,
                    lineHeight = 24.sp
                )

                Spacer(Modifier.height(28.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    GradientButton(
                        text = hero.ctaPrimary,
                        onClick = onNavigateAssessment,
                        icon = Icons.Rounded.Chat,
                        modifier = Modifier.weight(1f)
                    )
                    GradientButton(
                        text = hero.ctaSecondary,
                        onClick = onNavigateStories,
                        primary = false,
                        icon = Icons.Rounded.AutoStories,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(24.dp))

                // Success card
                GlassCard {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    Brush.linearGradient(listOf(Emerald500, Cyan500))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Rounded.Celebration, null, tint = Navy950, modifier = Modifier.size(26.dp))
                        }
                        Spacer(Modifier.width(14.dp))
                        Column {
                            Text(hero.cardBadge, color = Emerald400, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                            Text(hero.cardTitle, color = Slate100, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // ─── STATS ───
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SampleData.stats.forEach { stat ->
                GlassCard(modifier = Modifier.width(140.dp)) {
                    Text(
                        text = "${stat.value}${stat.suffix}",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Cyan400,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(stat.label, color = Slate400, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        // ─── FEATURES ───
        SectionHeader("چرا Startup Xpand؟", "مزیت‌های کلیدی مسیر استارتاپ")
        SampleData.features.forEach { f ->
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Cyan500.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(iconForFeature(f.icon), null, tint = Cyan400, modifier = Modifier.size(22.dp))
                    }
                    Spacer(Modifier.width(14.dp))
                    Column {
                        Text(f.title, color = Slate100, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text(f.desc, color = Slate400, style = MaterialTheme.typography.bodySmall, lineHeight = 20.sp)
                    }
                }
            }
        }

        // ─── IDEAS PREVIEW ───
        SectionHeader(
            title = "ایده‌های آماده",
            subtitle = "بانک ایده اعتبارسنجی‌شده",
            actionText = "همه ایده‌ها",
            onAction = onNavigateIdeas
        )
        SampleData.ideas.take(2).forEach { idea ->
            IdeaCardCompact(idea = idea, onClick = onNavigateIdeas)
        }

        // ─── CTA ───
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.horizontalGradient(listOf(Cyan600.copy(alpha = 0.25f), Amber500.copy(alpha = 0.15f)))
                )
                .border(1.dp, Cyan500.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    "آماده‌ای مسیرت رو شروع کنی؟",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Slate100,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "فرم ارزیابی رایگان را پر کن تا بهترین مسیر مهاجرتی رو بهت پیشنهاد بدیم.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Slate300,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(20.dp))
                GradientButton(
                    text = "شروع ارزیابی رایگان",
                    onClick = onNavigateAssessment,
                    icon = Icons.Rounded.RocketLaunch
                )
            }
        }

        Spacer(Modifier.height(100.dp))
    }
}

@Composable
private fun IdeaCardCompact(idea: com.startupxpand.app.data.ReadyIdea, onClick: () -> Unit) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickableNoIndication(onClick)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(idea.category, color = Cyan400, style = MaterialTheme.typography.labelMedium)
            StatusChip(idea.status, idea.statusText)
        }
        Spacer(Modifier.height(10.dp))
        Text(idea.title, color = Slate100, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(
            idea.description,
            color = Slate400,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            idea.techStack.take(3).forEach { TechChip(it) }
        }
    }
}

private fun Modifier.clickableNoIndication(onClick: () -> Unit): Modifier =
    this.clickable(onClick = onClick)
