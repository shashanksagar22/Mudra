package com.example.mudra

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mudra.data.ExpenseEntity
import com.example.mudra.ui.AddExpenseScreen
import com.example.mudra.ui.HomeScreen
import com.example.mudra.ui.theme.components.BottomNavigationBar
import com.example.mudra.ui.theme.components.BudgetTrackers
import com.example.mudra.ui.theme.components.CardSection
import com.example.mudra.ui.theme.components.CurrencySection
import com.example.mudra.ui.theme.components.SpendingExpensesCard
import com.example.mudra.ui.theme.components.WalletSection
import com.example.mudra.viewmodel.WalletViewModel

@Composable
fun AppNavHost(walletViewModel: WalletViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // Collect wallet balance and expenses from ViewModel
            val walletBalance by walletViewModel.walletBalanceFlow.collectAsState(initial = 0.0)
            val expenses by walletViewModel.expensesFlow.collectAsState(initial = emptyList())

            HomeScreen(
                navController = navController,
                walletViewModel = walletViewModel,
                walletBalance = walletBalance,
                expenses = expenses
            )
        }

        composable("add_expense") {
            AddExpenseScreen(navController = navController, walletViewModel = walletViewModel)
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController,
    walletViewModel: WalletViewModel,
    walletBalance: Double,
    expenses: List<ExpenseEntity>
) {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_expense") }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Expense")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            WalletSection(walletBalance = walletBalance)
            CardSection()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BudgetTrackers(modifier = Modifier.weight(1f))
                SpendingExpensesCard(
                    modifier = Modifier.weight(1f),
                    expenses = expenses,
                    onDelete = { /* TODO: implement delete action */ }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CurrencySection()
        }
    }
}
