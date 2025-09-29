package com.example.mudra

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mudra.viewmodel.WalletViewModel
import java.util.*

@Composable
fun AddExpenseScreen(navController: NavController, walletViewModel: WalletViewModel) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var dateMillis = System.currentTimeMillis()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Expense Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val amt = amount.toDoubleOrNull() ?: 0.0
                walletViewModel.addExpense(name, amt, dateMillis)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Expense")
        }

    }
}
