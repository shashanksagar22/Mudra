package com.example.mudra.ui.theme.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BudgetAndExpensesSection() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        if (maxWidth < 600.dp) {
            // Small screen (phones) → stack vertically
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                BudgetTrackers(modifier = Modifier.fillMaxWidth())
                SpendingExpensesCard(
                    modifier = Modifier.fillMaxWidth(),
                    expenses = TODO(),
                    onDelete = TODO()
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BudgetTrackers(modifier = Modifier.weight(1f))
                SpendingExpensesCard(
                    modifier = Modifier.weight(1f),
                    expenses = TODO(),
                    onDelete = TODO()
                )
            }
        }
    }
}
