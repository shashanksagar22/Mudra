package com.example.mudra

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mudra.data.ExpenseCategory
import com.example.mudra.ui.theme.DarkBlue

@Preview
@Composable
fun SpendingExpensesCard(modifier: Modifier = Modifier) {
    val expenseCategories = listOf(
        ExpenseCategory("Food", 0.25f, Color.Red),
        ExpenseCategory("Housing", 0.20f, Color.Green),
        ExpenseCategory("Bills", 0.15f, Color.Blue),
        ExpenseCategory("Transportation", 0.10f, Color.Cyan),
        ExpenseCategory("Other", 0.30f, DarkBlue)
    )

    Card(
        modifier = modifier
            .height(260.dp)
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
        ,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Spending Expenses",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = DarkBlue)
            Spacer(
                modifier = Modifier.height(8.dp))
            DonutChart(
                categories = expenseCategories,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseLegend(expenseCategories)
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = DividerDefaults.Thickness,
                color = DividerDefaults.color
            )
            ExpenseProgressItem("Utilities", "95%")
            ExpenseProgressItem("Utilities", "54%")

        }
    }
}


@Composable
fun DonutChart(categories: List<ExpenseCategory>, modifier: Modifier=Modifier) {
    Box(
        modifier = Modifier.size(100.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val total = categories.sumOf { it.percentage.toDouble() }.toFloat()
            var startAngle = -90f
            categories.forEach { category ->
                val sweepAngle = (category.percentage / total) * 360f
                drawArc(
                    color = category.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = 35f, cap = StrokeCap.Butt),
                    size = Size(size.width, size.height)
                )
                startAngle += sweepAngle
            }
        }
      //  Text("38%", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = DarkBlue)
    }
}

@Composable
fun ExpenseLegend(categories: List<ExpenseCategory>) {
    Column( // Makes the list scrollable
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp), // set height so it scrolls inside Card
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            LegendItem(category)
       // items(categories) { category ->
          //  LegendItem(category)
        }
    }
}
/*fun ExpenseLegend(categories: List<ExpenseCategory>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            LegendItem(categories[0])
            LegendItem(categories[1])
            LegendItem(categories[2])
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            LegendItem(categories[3])
            LegendItem(categories[4])
        }
    }
}*/
@Composable
fun LegendItem(category: ExpenseCategory) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color = category.color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(category.name, fontSize = 14.sp, color = Color.Gray)
    }
}
@Composable
fun ExpenseProgressItem(name: String, percentage: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name, color = DarkBlue)
        Text(percentage, color = DarkBlue, fontWeight = FontWeight.Bold)
    }
}