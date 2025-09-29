package com.example.mudra

import WalletViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.mudra.data.ExpenseDatabase
import com.example.mudra.repository.ExpenseRepository
import com.example.mudra.ui.theme.theme.MudraTheme
import com.example.mudra.viewmodel.WalletViewModelFactory
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize database, repository, and ViewModel
        val db = ExpenseDatabase.getDatabase(this)
        val repository = ExpenseRepository(db.expenseDao(), db.walletDao())
        val factory = WalletViewModelFactory(repository)
        val walletViewModel = ViewModelProvider(this, factory)[WalletViewModel::class.java]

        setContent {
            MudraTheme {
                SetBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(walletViewModel)
                }
            }
        }
    }
}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = color)
    }
}
