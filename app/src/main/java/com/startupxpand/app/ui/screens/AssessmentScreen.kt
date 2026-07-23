package com.startupxpand.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.startupxpand.app.ui.components.GlassCard
import com.startupxpand.app.ui.components.GradientButton
import com.startupxpand.app.ui.components.SectionHeader
import com.startupxpand.app.ui.theme.*

@Composable
fun AssessmentScreen() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var idea by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    if (submitted) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Navy950)
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            GlassCard {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Rounded.CheckCircle,
                        contentDescription = null,
                        tint = Emerald400,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        "درخواست شما ثبت شد!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Slate100,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "تیم مشاوره ما در کمتر از ۲۴ ساعت با شما تماس می‌گیرد.",
                        color = Slate400,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(24.dp))
                    GradientButton(
                        text = "ارسال درخواست جدید",
                        onClick = {
                            submitted = false
                            name = ""; phone = ""; age = ""; education = ""; idea = ""; country = ""
                        }
                    )
                }
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Navy950)
            .verticalScroll(rememberScrollState())
    ) {
        SectionHeader(
            title = "ارزیابی رایگان",
            subtitle = "فرم را پر کنید تا بهترین مسیر را پیشنهاد دهیم"
        )

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            GlassCard {
                FormField("نام و نام خانوادگی", name) { name = it }
                Spacer(Modifier.height(12.dp))
                FormField("شماره تماس", phone) { phone = it }
                Spacer(Modifier.height(12.dp))
                FormField("سن", age) { age = it }
                Spacer(Modifier.height(12.dp))
                FormField("مدرک تحصیلی", education) { education = it }
                Spacer(Modifier.height(12.dp))
                FormField("کشور مورد علاقه", country) { country = it }
                Spacer(Modifier.height(12.dp))
                FormField("توضیح کوتاه ایده یا هدف", idea, singleLine = false) { idea = it }
            }

            Spacer(Modifier.height(20.dp))

            GradientButton(
                text = "ارسال درخواست ارزیابی",
                onClick = { if (name.isNotBlank() && phone.isNotBlank()) submitted = true },
                icon = Icons.Rounded.Send,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))
            Text(
                "اطلاعات شما محرمانه است و فقط برای ارزیابی مسیر مهاجرتی استفاده می‌شود.",
                color = Slate500,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(100.dp))
        }
    }
}

@Composable
private fun FormField(
    label: String,
    value: String,
    singleLine: Boolean = true,
    onChange: (String) -> Unit
) {
    Column {
        Text(label, color = Slate400, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            singleLine = singleLine,
            minLines = if (singleLine) 1 else 3,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Cyan500,
                unfocusedBorderColor = Navy700,
                focusedContainerColor = Navy800,
                unfocusedContainerColor = Navy800,
                focusedTextColor = Slate100,
                unfocusedTextColor = Slate100,
                cursorColor = Cyan400
            ),
            shape = RoundedCornerShape(14.dp)
        )
    }
}
