package com.startupxpand.app.data

data class HeroContent(
    val badge: String,
    val titleLine1: String,
    val titleLine2: String,
    val titleLine3: String,
    val subtitle: String,
    val ctaPrimary: String,
    val ctaSecondary: String,
    val cardBadge: String,
    val cardTitle: String
)

data class ReadyIdea(
    val id: Int,
    val title: String,
    val status: String,
    val statusText: String,
    val seats: Int,
    val seatsText: String,
    val description: String,
    val detailedDesc: String,
    val marketPotential: String,
    val techStack: List<String>,
    val category: String,
    val primaryBtnText: String,
    val secondaryBtnText: String
)

data class MigrationMethod(
    val title: String,
    val tags: List<String>,
    val requirements: List<String>
)

data class Country(
    val code: String,
    val name: String,
    val methods: List<MigrationMethod>
)

data class SuccessStory(
    val id: String,
    val name: String,
    val country: String,
    val project: String,
    val duration: String,
    val industry: String,
    val description: String,
    val testimonial: String
)

data class FaqItem(
    val id: String,
    val question: String,
    val answer: String
)

data class FeatureItem(
    val icon: String,
    val title: String,
    val desc: String
)

data class StatItem(
    val value: String,
    val suffix: String,
    val label: String
)
