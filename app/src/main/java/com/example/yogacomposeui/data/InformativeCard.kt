package com.example.yogacomposeui.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.yogacomposeui.R
import com.example.yogacomposeui.ui.theme.BoyBlue
import com.example.yogacomposeui.ui.theme.OrangeRed

data class InformativeCard(
    val title: String,
    val value: String,
    val backgroundColor: Color,
    @DrawableRes val iconResource: Int
)

val informativeCards = listOf(
    InformativeCard(
        title = "Work Out",
        value = "232 min",
        iconResource = R.drawable.ic_baseline_fitness_center_24,
        backgroundColor = BoyBlue
    ),

    InformativeCard(
        title = "Heart Rate",
        value = "60 beats",
        iconResource = R.drawable.ic_monitor_heart_black_24dp,
        backgroundColor = OrangeRed
    )
)