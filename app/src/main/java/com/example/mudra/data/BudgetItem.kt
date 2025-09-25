package com.example.mudra.data

data class BudgetItem(
    val name: String,
    val allocated: String,
    val current: String,
    val max: String,
    val progress: Float
)