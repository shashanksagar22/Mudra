package com.example.mudra.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mudra.data.BudgetItem
import com.example.mudra.ui.theme.theme.DarkBlue

@Preview
@Composable
fun BudgetTrackers(modifier: Modifier= Modifier) {
    val budgetItems = listOf(
        BudgetItem("Groceries", "Allocated", "₹2,300", "₹5,000", 0.25f),
        // Add more budget items if needed
        BudgetItem("Housing", "Allocated", "₹1,500", "₹3,000", 0.1f),
        /*BudgetItem("Online Shopping",
            "Allocated",
            "₹500",
            "₹1000",
            0.15f)*/
    )
    Card(
        modifier= modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Budget Trackers",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue
            )
            Spacer(modifier = Modifier.height(16.dp))
            budgetItems.forEach { item ->
                BudgetTrackerItem(item)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun BudgetTrackerItem(item: BudgetItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = item.name, fontWeight = FontWeight.Bold, color = DarkBlue)
            Text(text = item.allocated, color = Color.Gray, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = item.current, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkBlue)
            Text(text = item.max, color = Color.Gray, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { item.progress },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
            color = Color(0xFF4CAF50),
            trackColor = Color.LightGray
        )
    }
}
