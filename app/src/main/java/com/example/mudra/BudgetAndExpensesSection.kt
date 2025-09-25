package com.example.mudra

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BudgetAndExpensesSection() {

    // Suppress the warning as the scope is being used correctly to access maxWidth
    @Suppress("BoxWithConstraintsScope")
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        if (maxWidth < 600.dp) {
            // Small screen (phones) → stack vertically
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                BudgetTrackers(modifier = Modifier.fillMaxWidth())
                SpendingExpensesCard(modifier = Modifier.fillMaxWidth())
            }
        } else {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BudgetTrackers(modifier = Modifier.weight(1f))
                SpendingExpensesCard(modifier = Modifier.weight(1f))
            }
        }
    }
}