package com.example.mudra.data

import androidx.compose.ui.graphics.Brush

data class Cards (
    val cardType: String,
    val cardNumber: String,
    val cardName: String,
    val balance: Long,
    val color: Brush
)