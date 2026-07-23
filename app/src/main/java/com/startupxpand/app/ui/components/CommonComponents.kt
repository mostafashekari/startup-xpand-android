package com.startupxpand.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startupxpand.app.ui.theme.*

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    primary: Boolean = true,
    icon: ImageVector? = null
) {
    val brush = if (primary) {
        Brush.horizontalGradient(listOf(Cyan500, Cyan600))
    } else {
        Brush.horizontalGradient(listOf(Navy800, Navy700))
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(brush)
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            if (icon != null) {
                Icon(icon, contentDescription = null, tint = if (primary) Navy950 else Slate100, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = text,
                color = if (primary) Navy950 else Slate100,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(CardDark)
            .border(1.dp, GlassBorder, RoundedCornerShape(24.dp))
            .padding(20.dp),
        content = content
    )
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = Slate100,
                fontWeight = FontWeight.Black
            )
            if (subtitle != null) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Slate400
                )
            }
        }
        if (actionText != null && onAction != null) {
            TextButton(onClick = onAction) {
                Text(actionText, color = Cyan400, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun StatusChip(status: String, statusText: String) {
    val (bg, fg) = when (status) {
        "available" -> Emerald500.copy(alpha = 0.15f) to Emerald400
        "last-seat" -> Amber500.copy(alpha = 0.15f) to Amber400
        "full" -> Rose500.copy(alpha = 0.15f) to Rose400
        else -> Cyan500.copy(alpha = 0.15f) to Cyan400
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(bg)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(statusText, color = fg, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TechChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Navy800)
            .border(1.dp, Navy700, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text, color = Slate300, style = MaterialTheme.typography.labelMedium, fontSize = 11.sp)
    }
}

@Composable
fun CountryFlagPlaceholder(code: String) {
    val emoji = when (code.lowercase()) {
        "ca" -> "🇨🇦"
        "de" -> "🇩🇪"
        "nl" -> "🇳🇱"
        "pt" -> "🇵🇹"
        "ae" -> "🇦🇪"
        "us" -> "🇺🇸"
        "fr" -> "🇫🇷"
        "es" -> "🇪🇸"
        else -> "🌍"
    }
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Navy800),
        contentAlignment = Alignment.Center
    ) {
        Text(emoji, fontSize = 24.sp)
    }
}

@Composable
fun AnimatedPressScale(
    onClick: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.96f else 1f,
        animationSpec = tween(100),
        label = "scale"
    )
    Box(
        modifier = Modifier
            .scale(scale)
            .clickable {
                pressed = true
                onClick()
                pressed = false
            }
    ) {
        content(Modifier)
    }
}

fun iconForFeature(name: String): ImageVector = when (name) {
    "shield" -> Icons.Rounded.Shield
    "lightbulb" -> Icons.Rounded.Lightbulb
    "users" -> Icons.Rounded.Groups
    "globe" -> Icons.Rounded.Public
    else -> Icons.Rounded.Star
}
