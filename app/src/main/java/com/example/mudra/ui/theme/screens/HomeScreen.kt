package com.example.mudra.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mudra.ui.theme.components.CardSection
import com.example.mudra.ui.theme.components.WalletSection
import com.example.mudra.viewmodel.WalletViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    walletViewModel: WalletViewModel,
    walletBalance: Any
) {
    val walletBalance by walletViewModel.walletBalanceFlow.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addExpense") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            WalletSection(walletBalance = walletBalance)
            CardSection()
            // TODO: connect SpendingExpensesCard with walletViewModel.expensesFlow
        }
    }
}
